package sample.cafekiosk.spring.api.service.mail;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sample.cafekiosk.spring.client.MailSendClient;
import sample.cafekiosk.spring.domain.history.MailSendHistoryRepository;

import static org.hamcrest.Matchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

class MailServiceTest {



    @DisplayName("메일 전송 테스트")
    @Test
    public void sendMail() {
        // given
        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);

        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        given(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString())).willReturn(true);

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        Assertions.assertThat(result).isTrue();
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistoryRepository.class));
    }

}