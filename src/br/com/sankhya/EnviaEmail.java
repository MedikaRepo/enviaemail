/* ------------------------------------------------
 * AUTOR:           ADRIANO
 * DATA DE CRIAÇAO: 16/10/2017
 * DESCRIÇÃO:       ENVIA EMAIL USANDO JAVA.MAIL
 --------------------------------------------------*/

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

import org.cuckoo.core.ScheduledAction;
import org.cuckoo.core.ScheduledActionContext;

public class EnviaEmail implements ScheduledAction
{
	@Override
	public void onTime(ScheduledActionContext arg0) 
      
      {
            Properties props = new Properties();
            
            // Parâmetros de conexão com servidor  
            props.put("mail.smtp.host", "email-ssl.com.br");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465"); 
            
            Session session = Session.getDefaultInstance(props,
                        new javax.mail.Authenticator() {
                             protected PasswordAuthentication getPasswordAuthentication() 
                             {
                                   return new PasswordAuthentication("adriano.soares@medika.com.br", "adrianomedika");
                             }
                        });
            
            // Ativa Debug para sessão 
            session.setDebug(true);
            try {

                  Message message = new MimeMessage(session);
                  //Remetente
                  message.setFrom(new InternetAddress("adriano.soares@medika.com.br")); 

                  //Destinatário(s)
                  Address[] toUser = InternetAddress 
                             .parse("gadrianosl@gmail.com, adriano.soares@medika.com.br");  
                  message.setRecipients(Message.RecipientType.TO, toUser);
                  
					Date data=new Date();
					GregorianCalendar gc = new GregorianCalendar();
			        gc.setTime(data);
			        gc.set(Calendar.DATE, gc.get(Calendar.DATE) - 68);
                  
                  message.setSentDate(gc.getTime());
                  message.setSubject("Enviando email com JavaMail - Re: 122892");//Assunto
                  message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
                  
                  //Método para enviar a mensagem criada
                  Transport.send(message);
                  
                  StringBuffer mensagem = new StringBuffer();
      		      mensagem.append("Email enviado!");
                  
                  System.out.println(mensagem);
                  
             } catch (MessagingException e) {
                  throw new RuntimeException(e);
            }
      } 
}
