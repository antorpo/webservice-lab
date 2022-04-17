package com.udea.controller;

import com.udea.exception.ModelNotFoundException;
import com.udea.model.Persona;
import com.udea.service.PersonaService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Antonio
 */
@RestController
@RequestMapping("/persona")
@CrossOrigin("*")
@Api(value="Employee Management System")
public class PersonaController {

    @Autowired
    PersonaService personService;

    @ApiOperation(value = "Add Persona")
    @PostMapping("/save")
    public long save(@ApiParam(value = "Empleado guardado en la BD", required = true) @RequestBody Persona person) {
        personService.save(person);
        return person.getIdPerson();

    }

    @ApiOperation(value = "View a list of available persons", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    @GetMapping("/listAll")
    public Iterable<Persona> listAllPersons() {
        return personService.list();
    }

    @ApiOperation(value = "Get a person by Id")
    @GetMapping("/list/{id}")
    public Persona listPersonById(@ApiParam(value = "Employee id from which employee object will retrieve", required = true)
            @PathVariable("id") int id) {
        Optional<Persona> person = personService.listId(id);
        if (person.isPresent()) {
            return person.get();
        }

        throw new ModelNotFoundException("ID de persona invalido");
    }
}
