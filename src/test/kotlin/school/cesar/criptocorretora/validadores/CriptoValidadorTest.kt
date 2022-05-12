package school.cesar.criptocorretora.validadores

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Cripto
import java.math.BigDecimal

class CriptoValidadorTest {

    @Test
    fun `nome do cripto em branco`(){
        val crypto = Cripto(1, "", BigDecimal(5))
        val cryptoValidator = CriptoValidador()
        assertThrows<RuntimeException> {
            //
            cryptoValidator.valida(crypto)
        }.also {
            Assertions.assertEquals("O campo nome deve ser preenchido", it.message)
        }
    }
}