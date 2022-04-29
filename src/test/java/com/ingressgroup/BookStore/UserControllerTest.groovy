package com.ingressgroup.BookStore

import com.ingressgroup.BookStore.controller.ErrorHandler
import com.ingressgroup.BookStore.controller.UserController
import com.ingressgroup.BookStore.dao.entity.UserEntity
import com.ingressgroup.BookStore.model.dto.BookCreate
import com.ingressgroup.BookStore.model.dto.UserDto
import com.ingressgroup.BookStore.model.exception.NotFoundException
import com.ingressgroup.BookStore.service.BookService
import com.ingressgroup.BookStore.service.UserService
import io.swagger.models.Response
import org.junit.jupiter.api.Test
import org.skyscreamer.jsonassert.JSONAssert
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.RequestBody
import spock.lang.Specification

import javax.print.attribute.standard.Media

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get


class UserControllerTest extends Specification {

    private BookService bookService
    private UserService userService
    private UserController userController
    private MockMvc mockMvc;

    def setup() {
        bookService = Mock()
        userService = Mock()
        userController = new UserController(bookService, userService)

        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ErrorHandler())
                .build()
    }

    def "GetBookWithPublisher - success"() {
        given:
        def publisher = "Ali"
        def endPoint = "/v1/user/book-publisher?publisher=Ali"
        def dto = new BookCreate().builder()
                .name("Mireli")
                .author("Abbasov").build()

        def expectedResponse = '''
                       [ {
                           "name" : "Mireli",
                           "author" : "Abbasov"
                        }]
                                    '''

        when:
        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()

        then:
        1 * bookService.getBookWithPublisher(publisher) >> List.of(dto)
        def response = result.response
        response.getStatus() == 200
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }

    def "GetBookWithPublisher-error"() {

        given:
        def publisher = "Ali"
        def endPoint = "/v1/user/book-publisher?publisher=Ali"

        def expectedResponse = '''
                        {

                           "code" : "NOT_FOUND",
                            "message" : "Publisher Ali not found"
                        }
                                    '''

        when:

        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:

        1 * bookService.getBookWithPublisher(publisher) >> {
            throw new NotFoundException("Publisher Ali not found", "NOT_FOUND")
        }
        def response = result.response
        response.getStatus() == 404
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }


    def "SignIn - success"() {

        given:
        def endPoint = "/v1/user/sing-in?email=eliabbasov764@gmail.com&password=1234"
        def email = "eliabbasov764@gmail.com"
        def password = "1234"
        def dto = "Hello Mireli Abbasov"


        when:
        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * userController.login(email, password) >> dto
        def response = result.response
        response.getStatus() == 200
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }

    def "SignIn- error"() {

        given:
        def endPoint = "/v1/user/sing-in?email=eliabbasov764@gmail.com&password=1234"
        def email = "eliabbasov764@gmail.com"
        def password = "1234"
        def expectedResponse = '''
                        {
                           "code" : "404",
                           "message" : "USER_NOT_FOUND"
                        }
                                    '''

        when:
        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn()

        then:
        1 * userController.login(email, password) >> {
            throw new NotFoundException("USER_NOT_FOUND", "404")
        }
        def response = result.getResponse()
        response.getStatus() == 404
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }


    def "SignUp - success"() {

        given:

        def endPoint = "/v1/user/sign-up"
        def dto = UserDto.builder()
                .name("Mirali")
                .surname("Abbasov")
                .email("eliabbasov764@gmail.com")
                .password("1234")
                .build()

        def entity = UserEntity.builder()
                .name("Mirali")
                .surname("Abbasov")
                .email("eliabbasov764@gmail.com")
                .password("1234")
                .build()


        def expectedResponse = '''
                       {
                           "id" :1,
                           "name" : "Mirali",
                           "surname" : "Abbasov",
                           "email" : "eliabbasov764@gmail.com",
                           "password" : "1234"
                         
                        }
                                    '''

        when:
        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON)

        )
                .andReturn()

        then:
        1 * userController.register(dto) >> entity
        def response = result.getResponse()
        response.getStatus() == 200
        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)

    }

    def "SignUp - error"() {

        given:
        def dto = UserDto.builder()
                .name("Mirali")
                .surname("Abbasov")
                .email("eliabbasov764@gmail.com")
                .password("1234")
                .build()

        def endPoint = "/v1/user/sign-up"
        def expectedResponse = '''
                        {
                        "code":"Email eliabbasov764@gmail.com already exist",
                        "message": "EMAIL_ALREADY_EXIST"
                        }
                '''

        when:
        def result = mockMvc.perform(get(endPoint)
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn()

        then:
        1 * userController.register(dto) >> {
            throw new NotFoundException("Email eliabbasov764@gmail.com already exist", "EMAIL_ALREADY_EXIST")
        }

        def response = result.response
        response.getStatus() == 404

        JSONAssert.assertEquals(expectedResponse, response.getContentAsString(), false)
    }


}
