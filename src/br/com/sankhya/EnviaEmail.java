package br.com.sankhya;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnviaEmail //implements AcaoRotinaJava 
{
     public static void main(String[] args)
      
	//@Override
	//public void doAction(ContextoAcao contexto) throws Exception 
      
      {
            Properties props = new Properties();
            /** Parâmetros de conexão com servidor  */
            props.put("mail.smtp.host", "email-ssl.com.br");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");

            //teste
            
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("adriano.soares@medika.com.br", "adrianomedika");
                             }
                        });
            /** Ativa Debug para sessão */
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  message.setFrom(new InternetAddress("adriano.soares@medika.com.br")); //Remetente

                  Address[] toUser = InternetAddress //Destinatário(s)
                             .parse("gadrianosl@gmail.com, adriano.soares@medika.com.br");  
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  
					Date data=new Date();
					GregorianCalendar gc = new GregorianCalendar();
			        gc.setTime(data);
			        gc.set(Calendar.DATE, gc.get(Calendar.DATE) - 68);
                  
                  message.setSentDate(gc.getTime());
                  message.setSubject("Enviando email com JavaMail - Re: 122892");//Assunto
                  message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
                  /**Método para enviar a mensagem criada*/
                  Transport.send(message);
                  
                  StringBuffer mensagem = new StringBuffer();
      		      mensagem.append("Email enviado!");

      			  //contexto.setMensagemRetorno(mensagem.toString());
                  
                  System.out.println(mensagem);
                  
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      }
}
