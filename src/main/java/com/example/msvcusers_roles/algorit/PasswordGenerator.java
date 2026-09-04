package com.example.msvcusers_roles.algorit;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator {
    // Grupos de caracteres permitidos
    private static final String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String NUMEROS = "0123456789";
    private static final String ESPECIALES = "!@#$%^&*()-_=+[{]};:\",<.>/?";

    // Generador aleatorio criptográficamente seguro
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generarContrasena(int longitud) {
        if (longitud < 8) {
            throw new IllegalArgumentException("La longitud mínima recomendada es de 8 caracteres.");
        }

        // Asegurar que la contraseña tenga al menos un carácter de cada tipo
        List<Character> caracteresContrasena = new ArrayList<>();
        caracteresContrasena.add(MINUSCULAS.charAt(RANDOM.nextInt(MINUSCULAS.length())));
        caracteresContrasena.add(MAYUSCULAS.charAt(RANDOM.nextInt(MAYUSCULAS.length())));
        caracteresContrasena.add(NUMEROS.charAt(RANDOM.nextInt(NUMEROS.length())));
        caracteresContrasena.add(ESPECIALES.charAt(RANDOM.nextInt(ESPECIALES.length())));

        // Llenar el resto de la longitud con una mezcla de todos los caracteres
        String todoJunto = MINUSCULAS + MAYUSCULAS + NUMEROS + ESPECIALES;
        for (int i = 4; i < longitud; i++) {
            caracteresContrasena.add(todoJunto.charAt(RANDOM.nextInt(todoJunto.length())));
        }

        // Mezclar los caracteres para que no sigan un orden predecible (ej. que no empiece siempre con minúscula)
        Collections.shuffle(caracteresContrasena, RANDOM);

        // Convertir la lista a un String final
        StringBuilder contrasenaFinal = new StringBuilder();
        for (char c : caracteresContrasena) {
            contrasenaFinal.append(c);
        }

        return contrasenaFinal.toString();
    }

    // Ejemplo de uso rápido
    public static void main(String[] args) {
        // Genera una contraseña segura de 14 caracteres
        String contrasenaNueva = generarContrasena(14);
        System.out.println("Contraseña generada: " + contrasenaNueva);
    }
}
