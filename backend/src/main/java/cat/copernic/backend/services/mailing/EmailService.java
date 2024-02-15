package cat.copernic.backend.services.mailing;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import cat.copernic.backend.data.models.administrator.Administrator;
import cat.copernic.backend.data.models.company.Company;
import cat.copernic.backend.data.models.student.Student;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
    private EmailConfig emailConfig = new EmailConfig();

    @Autowired
    private JavaMailSender sender = emailConfig.javaMailSender();

    public boolean sendEmail(String mailTo, String body, String subject) {
        boolean sendSuccess = false;
        MimeMessage message = sender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        try {
            helper.setTo(mailTo);
            helper.setText(body);
            helper.setSubject(subject);
            sender.send(message);
            sendSuccess = true;
        } catch(MessagingException e) {
            System.out.println("Error when sending the email: [" + e + "]");
        }

        return sendSuccess;
    }

    // Password recovery request email sender
    public void passwordRecoveryEmail(String destination, String validationCode) {
        String message = String.format(
            "Codi de validació => %s", validationCode
        );

        String subject = "Codi de validació de Copernic Jobs per a la recuperació de la contrasenya";

        new Thread(new SendEmail(destination, message, subject)).start();
    }

    
    // Students New offer notification email sender
    public void newOfferNotificationEmail(List<Student> students, String offerName, String companyName) {
        String message = String.format(
            "Nova oferta publicada => %s \nper part de l'empresa %s", offerName, companyName
        );

        String subject = "Noves ofertes publicades";

        for (Student student : students) {
            new Thread(new SendEmail(student.getEmail(), message, subject)).start();
        }
    }

    // New Student Inscription to a offer, notification
    public void newInscriptionNotificationEmail(Student student, String offerName) {
        String message = String.format(
            "Inscripció correcta a l'oferta => %s \nper l'usuari %s", offerName, student.getEmail()
        );

        String subject = "Inscripció satisfactòria";


        if(student.getNewInscriptionNotification()) {
            new Thread(new SendEmail(student.getEmail(), message, subject)).start();
        }
    }


    // New Solved Report Inscription Notification
    public void solvedReportNotification(Company company, String reportTitle) {
        String message = String.format(
            "El vostre informe %s ha estat resolt per un administrador", reportTitle
        );

        String subject = "Informe resolt";


        if(company.isResolvedReportNotification()) {
            new Thread(new SendEmail(company.getEmail(), message, subject)).start();
        }
    }


    // New Report Notification
    public void newReportNotification(List<Administrator> administrators, String reportTitle, String userReport) {
        String message = String.format(
            "Nova notificació d'informe %s\nper part de l'usuari %s", reportTitle, userReport
        );

        String subject = "Nou Informe";

        for (Administrator administrator : administrators) {
            if(administrator.getNewReportNotification()) {
                new Thread(new SendEmail(administrator.getEmail(), message, subject)).start();
            }
        }
    }


    // New Access request Notification
    public void newAccessRequestNotification(List<Administrator> administrators, String accessRequester) {
        String message = String.format(
            "Nova sol·licitud d'accés per %s", accessRequester
        );

        String subject = "Nova sol·licitud d'accés";

        for (Administrator administrator : administrators) {
            if(administrator.getNewReportNotification()) {
                new Thread(new SendEmail(administrator.getEmail(), message, subject)).start();
            }
        }
    }


    // New Access request Notification
    public void accessNotificationStatus(String destination, String status) {
        String message = String.format(
            "La teva nova sol·licitud d'accés ha estat %s", status
        );

        String subject = "Nova sol·licitud d'accés";

        new Thread(new SendEmail(destination, message, subject)).start();
    }

    // Student offer unsubscription notification
    public void unsubscriptionNotification(String studentEmail, String offerName, String destination) {
        String message = String.format(
            "L'usuari %s ha cancel·lat l'inscripció a l'oferta %s", studentEmail, offerName
        );

        String subject = "Nova inscripció cancel·lada a una oferta";

        new Thread(new SendEmail(destination, message, subject)).start();
    }



    // -----------------------------------------------------------------------------------------------------------
    // Send a new email thread class
    private class SendEmail implements Runnable {
        private String destination;
        private String message;
        private String subject;
    
        public SendEmail(String destination, String message, String subject) {
            this.destination = destination;
            this.message = message;
            this.subject = subject;
        }
    
        @Override
        public void run() {
            System.out.println("Email Send Started! <-----");
            boolean emailSendStatus = sendEmail(destination, message, subject);
    
            if(emailSendStatus) {
                System.out.println("Email Sended Successfully!");
            }else {
                System.out.println("Error Sending email!");
            }
        }
    }
}