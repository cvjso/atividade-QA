package school.cesar.criptocorretora.validadores

import io.mockk.mockk
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import school.cesar.criptocorretora.entidades.Carteira
import school.cesar.criptocorretora.entidades.Usuario
import school.cesar.criptocorretora.util.CPFUtil
import school.cesar.criptocorretora.util.EmailUtil
import school.cesar.criptocorretora.util.SenhaUtil


class UsuarioValidatorTest {

    @Test
    fun `usuario com nome em branco`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"11122233344","","teste@gmail.com","Teste123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O nome deve ser preenchido", it.message)
        }

    }

    @Test
    fun `usuario com cpf em branco`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"","tenorio","teste@gmail.com","Teste123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O cpf deve ser preenchido", it.message)
        }

    }

    @Test
    fun `usuario com email em branco`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"11122233344","tenorio","","Teste123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O e-mail deve ser preenchido", it.message)
        }

    }

    @Test
    fun `usuario com senha em branco`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"11122233344","tenorio","teste@email.com","", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O senha deve ser preenchido", it.message)
        }

    }

    @Test
    fun `usuario com nome maior que 200 caracteres`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"11122233344","tenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenoriotenorio","teste@email.com","12345678", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O campo nome deve ter menos de 200 caracteres", it.message)
        }
    }

    @Test
    fun `usuario com cpf de tamanho não valido`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"111","tenorio","teste@email.com","12345678", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O campo cpf deve ter 11 caracteres numericos", it.message)
        }
    }

    @Test
    fun `usuario com senha de tamanho nao valido`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"11122233344","tenorio","teste@email.com","123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O campo confirmação senha deve ter entre 8 e 15 caracteres", it.message)
        }
    }

    @Test
    fun `usuario com cpf invalido`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"1112223334A","tenorio","teste@email.com","Teste123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O cpf é invalido", it.message)
        }
    }

    @Test
    fun `usuario com email invalido`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"12136104438","tenorio","teste@email","Teste123", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O a emal deve seguir o formato palavra@palavra.palavra", it.message)
        }
    }

    @Test
    fun `usuario com senha invalida`(){
        val cpfUtil = CPFUtil()
        val emailUtil = EmailUtil()
        val senhaUtil = SenhaUtil()

        val usuario = Usuario(1,"12136104438","tenorio","teste@email.com","12345678", Carteira())
        val usuarioValidador = UsuarioValidator(cpfUtil, emailUtil, senhaUtil)
        assertThrows<RuntimeException> {
            usuarioValidador.valida(usuario)
        }.also {
            Assertions.assertEquals("O a senha deve conter numeros, letras maisculas e minusculas", it.message)
        }
    }
}