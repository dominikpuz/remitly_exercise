package com.remitly.exercise;

import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter file path");
            String path = sc.nextLine();
            System.out.println(JSONResourceVerifier.verify(path));
        } catch (IOException e) {
            System.err.println("Could not read " + e.getMessage());
        } catch (JSONException e) {
            System.err.println(e.getMessage());
        }
    }
}