package school.cesar.criptocorretora.services

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Cripto
import school.cesar.criptocorretora.entidades.Usuario
import java.math.BigDecimal

class CarteiraServiceTest {

    @Test
    fun `função de comprar`(){
        val userService = mockk<UsuarioService>()
        val criptoService = mockk<CriptoService>()

        val usuario = Usuario(1,"12136104438","tenorio","tenorio@gmail.com","Teste123", Carteira() )
        val cripto = Cripto(1,"teste", BigDecimal(5.13))
        every { userService.buscarPorId(1) } returns usuario
        every { criptoService.buscarPorId(1) } returns cripto

        val carteiraService = CarteiraService(userService, criptoService)
        carteiraService.comprar(1,1,BigDecimal(5.13))
        usuario.carteira.cripto[cripto]?.let { Assertions.assertEquals(1, it.toInt()) }
    }

    @Test
    fun `função de consultar`(){
        val userService = mockk<UsuarioService>()
        val criptoService = mockk<CriptoService>()

        val usuario = Usuario(1,"12136104438","tenorio","tenorio@gmail.com","Teste123", Carteira() )
        every { userService.buscarPorId(1) } returns usuario
        val carteiraService = CarteiraService(userService, criptoService)
        Assertions.assertEquals(carteiraService.consultarValoresAgrupados(1), usuario.carteira.cripto)
    }
}