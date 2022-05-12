package school.cesar.criptocorretora.services

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.builders.CriptoBuilder
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.repositories.CriptoRepository
import school.cesar.criptocorretora.repositories.UsuarioRepository
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil
import school.cesar.criptocorretora.validadores.CriptoValidador
import school.cesar.criptocorretora.validadores.UsuarioValidator
import java.math.BigDecimal

class UsuarioServiceTest {

    val cpfUtil = CPFUtil()
    val emailUtil = EmailUtil()
    val senhaUtil = SenhaUtil()
    val usuarioRepository = UsuarioRepository()

    val usuarioValidator = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
    val usuarioService = UsuarioService(usuarioValidator, usuarioRepository)

    @Test
    fun `Buscar por usuario valido existente`(){
        val usuario = Usuario(1,"12136104438","tenorio","teste@gmail.com","Teste123", Carteira())
        usuarioService.adicionar(usuario)
        Assertions.assertEquals(usuarioService.buscarPorId(1).id, 1)
    }

    @Test
    fun `Buscar por usuario nao existente`(){
        val usuario = Usuario(1,"12136104438","tenorio","teste@gmail.com","Teste123", Carteira())
        usuarioService.adicionar(usuario)
        assertThrows<RuntimeException> {
            usuarioService.buscarPorId(2)
        }.also {
            Assertions.assertEquals("Id Não encontrado", it.message)
        }

    }
}