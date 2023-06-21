package sample.cafekiosk.unit.beverage;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AmericanoTest {

    @DisplayName("이름")
    @Test
    public void getName() {
        Americano americano = new Americano();
        assertThat(americano.getName()).isEqualTo("아메리카노");
    }

    @DisplayName("가격")
    @Test
    public void getPrice() {
        Americano americano = new Americano();
        assertThat(americano.getPrice()).isEqualTo(4000);
    }

}