package br.com.dbc.vemser.pessoaapi.service;

import br.com.dbc.vemser.pessoaapi.dto.ContatoDTO;
import br.com.dbc.vemser.pessoaapi.dto.EnderecoDTO;
import br.com.dbc.vemser.pessoaapi.dto.PessoaDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    @Value("${spring.mail.username}")
    private String emailPropertie;

    @Value("${spring.mail.username}")
    private String para;

    private final JavaMailSender emailSender;

    //Envia uma mensagem simples sem template ou anexos
    public void sendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(emailPropertie);
        message.setTo(para);
        message.setSubject("Assunto");
        message.setText("Teste\n minha mensagem \n\nAtt,\nSistema.");
        emailSender.send(message);
    }

    //Eniva uma mensgem simples + Anexo
    public void sendWithAttachment() throws MessagingException {
        MimeMessage message = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(message,
                true);

        helper.setFrom(emailPropertie);
        helper.setTo(para);
        helper.setSubject("Assunto");
        helper.setText("Teste\n minha mensagem \n\nAtt,\nSistema.");

        File file1 = new File("imagem.jpg");
        FileSystemResource file
                = new FileSystemResource(file1);
        helper.addAttachment(file1.getName(), file);

        emailSender.send(message);
    }

    // --------------------------------------------------------------------------------------------------------------- //
    // Métodos para cadastro, update e delete das classes.
    // -> Responsáveis por definir o assunto do email e o template que serão usados.

    // Pessoa -------------------------------------------------------------------------------------------------------- //

    //Envia email para confirmar cadastro!
    public void sendEmailCadastroPessoa(PessoaDTO pessoaDTO){
        sendEmailPessoa(pessoaDTO, "Cadastro Concluído com Sucesso!", "cadastro-template.ftl");
    }

    //Envia email para confirmar atualização do dados!
    public void sendEmailUpdatePessoa(PessoaDTO pessoaDTO){
        sendEmailPessoa(pessoaDTO, "Atualização de dados", "update-template.ftl");
    }

    //Envia email para confirmar remoção da pessoa
    public void sendEmailDeletePessoa(PessoaDTO pessoaDTO){
        sendEmailPessoa(pessoaDTO, "Encerramento da Conta", "delete-template.ftl");
    }

    // Endereço ------------------------------------------------------------------------------------------------------ //

    //Envia email informando que um endereço foi cadastrado
    public void sendEmailEnderecoCadastrado(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO){
        sendEmailEndereco(pessoaDTO, enderecoDTO, "Endereço Cadastrado com Sucesso!", "endereco-cadastro-template.ftl");
    }

    //Envia email informando que um endereço foi atualizado
    public void sendEmailEnderecoUpdate(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO){
        sendEmailEndereco(pessoaDTO, enderecoDTO, "Endereço Atualizado com Sucesso!", "endereco-update-template.ftl");
    }

    //Envia email informando que um endereço foi removido
    public void sendEmailEnderecoDeletado(EnderecoDTO enderecoDTO, PessoaDTO pessoaDTO){
        sendEmailEndereco(pessoaDTO, enderecoDTO, "Endereço Removido com Sucesso!", "endereco-delete-template.ftl");
    }

    // Contato ------------------------------------------------------------------------------------------------------- //

    //Envia email informando que um contato foi cadastrado
    public void sendEmailContatoCadastrado(ContatoDTO contatoDTO, PessoaDTO pessoaDTO) {
        sendEmailContato(pessoaDTO, contatoDTO, "Contato Cadastrado com Sucesso!", "Contato-cadastro-template.ftl");
    }

    //Envia email informando que um endereço foi atualizado
    public void sendEmailContatoUpdate(ContatoDTO contatoDTO, PessoaDTO pessoaDTO){
        sendEmailContato(pessoaDTO, contatoDTO, "Contato Atualizado com Sucesso!", "Contato-update-template.ftl");
    }

    //Envia email informando que um endereço foi removido
    public void sendEmailContatoDeletado(ContatoDTO contatoDTO, PessoaDTO pessoaDTO){
        sendEmailContato(pessoaDTO, contatoDTO, "Contato Removido com Sucesso!", "Contato-delete-template.ftl");
    }

    // --------------------------------------------------------------------------------------------------------------- //
    // Métodos mais genericos para cada classe
    // -> Responsável por selecioanr os elementos obrigatórios de cada email, acessar o diretório especifico dos templates
    //    e chamar o método genérico

    private void sendEmailPessoa(PessoaDTO pessoaDTO, String subject, String templateName) {

        //Mapeamento das informações que serão enviadas ao template.
        Map<String, Object> dados = new HashMap<>();
        //Informações da pessoa
        dados.put("nome", pessoaDTO.getNome());
        dados.put("id", pessoaDTO.getIdPessoa());
        //Informação do suporte
        dados.put("email", emailPropertie);

        templateName = "/pessoa/" + templateName;
        sendEmail(pessoaDTO.getEmail(), dados, subject, templateName);
    }

    private void sendEmailEndereco(PessoaDTO pessoaDTO, EnderecoDTO enderecoDTO, String subject, String templateName) {

        //Mapeamento das informações que serão enviadas ao template.
        Map<String, Object> dados = new HashMap<>();
        //Informações da Pessoa
        dados.put("nome", pessoaDTO.getNome());
        //Infomração do endereco
        dados.put("logradouro", enderecoDTO.getLogradouro());
        dados.put("numero", enderecoDTO.getNumero());
        //Informação do Suporte
        dados.put("email", emailPropertie);

        templateName = "/endereco/" + templateName;
        sendEmail(pessoaDTO.getEmail(), dados, subject, templateName);
    }

    private void sendEmailContato(PessoaDTO pessoaDTO, ContatoDTO contatoDTO, String subject, String templateName) {

        //Mapeamento das informações que serão enviadas ao template.
        Map<String, Object> dados = new HashMap<>();
        //Informações da Pessoa
        dados.put("nome", pessoaDTO.getNome());
        //Infomração do contato
        dados.put("numero", contatoDTO.getNumero());
        //Informação do Suporte
        dados.put("email", emailPropertie);

        templateName = "/contato/" + templateName;
        sendEmail(pessoaDTO.getEmail(), dados, subject, templateName);
    }

    private void sendEmail(String email, Map<String, Object> dados, String subject, String templateName) {

        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(emailPropertie);
            mimeMessageHelper.setTo(email);
            mimeMessageHelper.setSubject(subject);

            mimeMessageHelper.setText(getContentFromTemplate(templateName, dados), true);

            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.printStackTrace();
        }
    }

    private String getContentFromTemplate(String arquivoTemplate, Map<String, Object> dados) throws IOException, TemplateException {

        Template template = fmConfiguration.getTemplate(arquivoTemplate);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, dados);
        return html;
    }

}
