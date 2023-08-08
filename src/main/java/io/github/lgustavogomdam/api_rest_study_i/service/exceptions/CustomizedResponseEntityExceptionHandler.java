package io.github.lgustavogomdam.api_rest_study_i.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/*
===========================================|ControllerAdvice|===========================================================

    É uma anotação que indica ao compilador em tempo de runtime que a seguinte classe é, em outras
    palavras, uma controladora de exceções (por isso, a classe comumente é anotada também por @RestController), logo,
    sempre que uma exceção for disparada o compilador procurará uma solução aqui e caso exista um "endpoint" dessa
    exceção ele chama o método responsável por ela, caso não exista, ele chama um tratamento de exceção global
    que é o "handleAllExceptions"

===========================================|ExceptionHandler|===========================================================

    É usada para anotar métodos que tratam exceções em um aplicativo Spring MVC. Os métodos anotados com
    @ExceptionHandler são chamados quando uma exceção é lançada em um controlador. Esses métodos têm acesso à exceção
    que foi lançada e podem ser usados para gerar uma resposta HTTP personalizada ou redirecionar a solicitação para
    um URL diferente. A anotação @ExceptionHandler tem um parâmetro para o tipo de exceção que o método deve tratar.
    Se uma exceção não corresponder ao tipo especificado, o método não será chamado.

    OBS: note que a declaração da anotação mos métodos anotados por "@HandlerException" sempre possuem um parametro que
    indica de qual exceção se trata, se não existir nenhuma específica ele chama a que tem o parametro "Exception.class"
    que é a exceção mais genérica possível.
*/
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleNotFoundExceptions(Exception ex, WebRequest request){
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}

/*
==============================================|Informações Úteis|=======================================================
*
======================|ResponseEntity

    ResponseEntity é uma classe do Spring Framework que representa uma resposta HTTP. Ele contém o corpo da resposta,
    o status da resposta e as cabeçalhos da resposta.O corpo da resposta pode ser um objeto, uma string ou um array.
    O status da resposta é um código de status HTTP, como 200 OK, 404 Not Found ou 500 Internal Server Error.
    Os cabeçalhos da resposta são pares de nome-valor que podem ser usados para transmitir informações adicionais com
    a resposta. O ResponseEntity pode ser usado para retornar respostas de métodos de controlador, filtros e
    anotadores @ExceptionHandler. Ele também pode ser usado para retornar respostas de serviços REST.

======================|WebRequest

    No Spring, WebRequest é uma interface que representa uma solicitação HTTP. Ele fornece acesso aos parâmetros
    da solicitação, cabeçalhos e corpo. A interface WebRequest é usada por vários componentes do Spring, como filtros,
    interceptadores e anotadores @ControllerAdvice. Esses componentes podem usar a interface WebRequest para obter
    informações sobre a solicitação HTTP e tomar decisões sobre como processá-la. A interface WebRequest também é
    usada por componentes do Spring que fornecem serviços REST. Esses componentes podem usar a interface WebRequest
    para obter informações sobre a solicitação HTTP e gerar uma resposta HTTP.

    Diferente da RequestEntity que é usada para criar e enviar uma requisição, o WebRequest é usado comumente apenas
    para obter informações a respeito de uma requisição já feita.
*/