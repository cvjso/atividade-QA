package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DollarServiceTest {

    @Test
    fun `teste pegar valor`(){
        val dollarService = DollarService()
        val valor = dollarService.pegarValorDollarAtual().toDouble()
        Assertions.assertTrue(valor < 5.1 && valor > 3.9 )
    }
}