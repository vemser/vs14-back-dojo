<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Atualização de Contato Concluída</title>
        <style>
            .container {
                background-color: #d3be6c;
                color: #000;
                text-align: center;
            }
            .header {
                font-size: 48px;
                color: blue;
                margin-bottom: 20px;
            }
            .content {
                font-size: 24px;
                color: #555100;
                margin-bottom: 20px;
            }
            .footer {
                margin-top: 20px;
                font-size: 18px;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <b>Atualização de Contato Concluída</b>
            </div>
            <div class="content">
                <p>Olá ${nome},</p>
                <p>Ficamos felizes em comunicar que já realizamos a atualização do seu contato registrado no número: ${numero}</p>
                <p>Obrigado por confiar no nosso sistema de pessoas! :-)</p>
            </div>
            <div class="footer">
                <p>Qualquer dúvida é só contatar o suporte pelo e-mail ${email}</p>
                <p><b>Att, Sistema</b></p>
            </div>
        </div>
    </body>
</html>