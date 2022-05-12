package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.validadores.CriptoValidador
import java.math.BigDecimal

class CriptoServiceTest {
    val criptoBuilder = CriptoBuilder()
    val criptoValidador = CriptoValidador()
    val criptoRepository = CriptoRepository()

    val criptoService = CriptoService(criptoBuilder, criptoValidador, criptoRepository)

    @Test
    fun `Buscar por usuario valido existente`(){
        criptoService.add("teste", BigDecimal(5.13))
        Assertions.assertEquals(criptoService.buscarPorId(0).id, 0)
    }

    @Test
    fun `Buscar por usuario nao existente`(){
        criptoService.add("teste", BigDecimal(5.13))
        assertThrows<RuntimeException> {
            criptoService.buscarPorId(2)
        }.also {
            Assertions.assertEquals("Id não existente", it.message)
        }

    }
}