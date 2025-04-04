package applications.census;

import libraries.dataStructures.models.ListPOI;
import libraries.dataStructures.linear.SortedLinkedListPOI;
import java.util.ArrayList;
import java.util.Random;
import java.util.Collections;
import java.util.Scanner;

/** Test del codigo desarrollado en la practica **/

public class TestCensus {
    private static final int SIZE = 30;
    
    public static void list() {
        System.out.println("EXAMPLE. VoterList. " + SIZE + " VOTERS. UNSORTED.");
        System.out.println("---------------------------------------------------");
        VoterList a = new VoterList(false, SIZE);
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        
        System.out.println("EXAMPLE. VoterList. " + SIZE + " VOTERS. SORTED LIST.");
        System.out.println("---------------------------------------------------");
        VoterList b = new VoterList(true, SIZE);
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }
    
    public static void check() {
        boolean ok = mostrar(testListaOrdenada()) 
                     && mostrar(testResident()) 
                     && mostrar(testIndice()) 
                     && mostrar(testVoterList()); 
        if (ok) System.out.println("*** CORRECT ***"); 
        else    System.out.println("*** ERRORS WERE FOUND ***"); 
    }
    
    public static void listPC() { 
        VoterList a = new VoterList(true, 10 * SIZE);
        System.out.println("EXAMPLE. VoterList. " + a.getSize() + " VOTERS. SORTED LIST.");
        System.out.println("---------------------------------------------------");        
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        System.out.println("Input the postal code range to limit the VoterList.");
        System.out.println("Input the lower bound or minimum postal code:"); 
        Scanner sc = new Scanner(System.in);
        int cp1 = sc.nextInt();
        System.out.println("Input the upper bound or maximum postal code:"); 
        int cp2 = sc.nextInt();
        VoterList b = a.getLocalCensus(cp1, cp2);
        System.out.println("EXAMPLE. VoterList. " + b.getSize() 
            + " VOTERS IN POSTAL CODES [" + cp1 + " .. " + cp2 + "]:");
        System.out.println("---------------------------------------------------");        
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }
    
    public static void search() { 
        VoterList a = new VoterList(true, 10 * SIZE);
        System.out.println("EXAMPLE. VoterList. " + a.getSize() + " VOTERS. SORTED LIST.");
        System.out.println("---------------------------------------------------");        
        System.out.println(a);
        System.out.println("---------------------------------------------------\n");
        System.out.println("Input the surname prefix to limit the VoterList:");
        Scanner sc = new Scanner(System.in);
        String prefijo = sc.next().toUpperCase();
        VoterList b = a.search(prefijo);
        System.out.println("EXAMPLE. VoterList. " + b.getSize() 
            + " VOTERS WITH SURNAMES STARTING WITH " + prefijo);
        System.out.println("---------------------------------------------------");        
        System.out.println(b);
        System.out.println("---------------------------------------------------\n");
    }
    
    private static boolean mostrar(boolean ok) {
        if (ok) System.out.println("\tCorrect"); 
        else    System.out.println("\tError"); 
        return ok;
    }
    
    private static boolean testListaOrdenada() {
        System.out.println("Checking the SortedLinkedListPOI class...");
        ListPOI<Integer> lista = new SortedLinkedListPOI<>(); 
        ArrayList<Integer> v = new ArrayList<>();
        Random r = new Random();
        for (int i = 1; i <= SIZE * 100; i++) {
            int n = r.nextInt(); 
            v.add(n);
            lista.add(n);
        }
        Collections.sort(v);
        if (lista.size() != v.size()) return false; 
        int i = 0;
        for (lista.begin(); !lista.isEnd(); lista.next(), i++) {
            if (!(v.get(i).equals(lista.get()))) return false;
        }
        return true;
    }
    
    private static boolean testResident() {
        System.out.println("Checking the Resident class...");
        for (int i = 1; i <= SIZE; i++) {
            Resident a = new Resident();
            Resident b = new Resident();            
            Resident c = b;
            if (!c.equals(b)) return false; 
            if (c.compareTo(b) != 0) return false;             
            if (a.toString().equals(b.toString())) {
                if (!a.equals(b)) return false; 
                if (a.compareTo(b) != 0) return false; 
            } 
            else {
                if (a.equals(b)) return false; 
                if (a.toString().compareTo(b.toString()) < 0) {
                    if (a.compareTo(b) >= 0) return false; 
                } 
                else if (a.compareTo(b) <= 0) return false; 
            }
        }
        return true;
    }
    
    private static boolean testIndice() {
        System.out.println("Checking the index method of the VoterList class... ");
        return testIndice(false) && testIndice(true);
    }
    
    private static boolean testIndice(boolean ordenada) {
        VoterList a = new VoterList(ordenada, 1000);
        ArrayList<Resident> c = obtenerCenso(a);
        try {
            for (Resident h : c) {
                int pos = a.index(h);
                if (pos == -1) return false; 
                else { if (pos != c.indexOf(h)) return false; }
            }            
            for (int i = 0; i <= SIZE; i++) {
                Resident h = new Resident();
                int pos = a.index(h);
                if (pos == -1) { if (c.contains(h)) return false; } 
                else { if (pos != c.indexOf(h)) return false; }
            }
        } catch (Exception e) { return false; }
        return true;
    }
    
    private static boolean testVoterList() {
        System.out.println("Checking the constructor of the VoterList class... ");
        return testVoterList(false) && testVoterList(true);
    }

    private static boolean testVoterList(boolean ordenada) {
        int size = 1000;
        for (int i = 0; i <= SIZE; i++) {
            VoterList a = new VoterList(ordenada, size);
            ArrayList<Resident> c = obtenerCenso(a);
            if (c.size() != size) return false; 
            ArrayList<String> lista = new ArrayList<>();
            Resident prev = new Resident("", "", "", "", 0);
            for (int j = 0; j < size; j++) {  
                Resident h = c.get(j);
                String dni = h.getDni();
                if (lista.contains(dni)) return false; 
                lista.add(dni);
                if (ordenada && h.compareTo(prev) < 0) return false; 
                prev = h;
            }
        }
        return true;
    }
        
    private static ArrayList<Resident> obtenerCenso(VoterList a) {
        ArrayList<Resident> c = new ArrayList<>();        
        a.getCensus().begin();
         for (int i = 0; i < a.getCensus().size(); i++) {
            c.add(a.getCensus().get());
            a.getCensus().next();
        }
        return c;
    }
}
