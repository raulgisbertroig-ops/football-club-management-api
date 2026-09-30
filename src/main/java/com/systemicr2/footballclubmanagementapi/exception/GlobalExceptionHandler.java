package com.systemicr2.footballclubmanagementapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        Map<String, String> response = new HashMap<>();
        response.put("error", "Violación de Regla de Negocio");
        response.put("message", ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    // Le decimos que atrape cualquier RunTimeException lanzado en tus servicios
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, Object>> handleRuntimeException(RuntimeException ex) {

        // Construimos el JSON de respuesta
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", HttpStatus.BAD_REQUEST.value()); // HTTP 400
        body.put("error", "Petición Invalida");
        body.put("message", ex.getMessage()); // Aquí inyecta tu texto: "No se pueden exceder los 7 titulares..."

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {

        // RAM: Reservamos un bloque de memoria volátil para estructurar nuestra respuesta final.
        // HashMap es ideal porque organiza los datos en "Clave -> Valor", consumiendo poco ciclo de CPU al buscar.
        Map<String, String> errors = new HashMap<>();

        // CPU / RAM: Extraemos la lista de errores que provocó el cliente.
        // Usamos un bucle forEach (programación funcional). La CPU iterará sobre cada error encontrado en la RAM.
        ex.getBindingResult().getAllErrors().forEach((ObjectError error) -> {

            // CPU: Ejecuta un "Casting" de memoria. Transforma el error genérico en un FieldError específico
            // para poder leer qué variable exacta (ej. 'teamId') provocó el fallo.
            String fieldName = ((FieldError) error).getField();

            // RAM: Extrae el mensaje de texto (ej. "No puede estar vacío") y lo guarda en memoria temporal.
            String errorMessage = error.getDefaultMessage();
            // DISCO/RAM: Insertamos ambos datos en nuestro HashMap.
            errors.put(fieldName, errorMessage);
        });
        // RED / RAM: La CPU empaqueta el HashMap en una respuesta HTTP y la empuja por el puerto de red al cliente.
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
