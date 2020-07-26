package com;

import java.util.ArrayList;
import java.util.List;

public class URLFactory {
    private String path;
    private List<String> packages = new ArrayList<>();
    private int[] slashPosition = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
            -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};

    /**
     * Konstruktor umożliwiający edycje wprowadzonej ścieżki poprzez usuwanie package i dodawanie nowych.
     *
     * @param path Scieżka dostępu do aktualnego package, zwrócona przy użyciu metod
     *             <b>getClass().getResource("").getPath()</b>
     */
    public URLFactory(String path) {
        this.path = path;
        setSlashPosition();
        setPackages();
    }

    /**
     * Ustawią nową ścieżkę oraz aktualizuje dane o package
     *
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
        setSlashPosition();
        setPackages();
    }

    /**
     * Funkcja zapisuje indexy występowania znaku "/" w ścieżce.
     */
    private void setSlashPosition() {
        int a = 0;
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '/') {
                slashPosition[a] = i;
                a++;
            }
        }
    }

    /**
     * Wyobrębnia nazwy package/folderow ze sciezki.
     */
    private void setPackages() {
        StringBuilder sb = new StringBuilder(path);

        for (int i = 0; i < slashPosition.length - 1; i++) {
            if (slashPosition[i + 1] != -1) {
                String s = sb.substring(slashPosition[i] + 1, slashPosition[i + 1]);
                packages.add(s);
            }
        }
    }

    /**
     * Czyści zapisane package
     */
    public void clearPackages() {
        packages.clear();
    }

    public String getPath() {
        return path;
    }

    /**
     * Funkcja usuwa od lewej strony ilość package wprowadzonych jako parametr i zwraca wynikową ścieżkę.
     *
     * @param nr Ilość package - licząc od końca
     * @return Nowa scieżka
     */
    public String path(int nr) {
        StringBuilder s = new StringBuilder("file:/");

        for (int i = 0; i < packages.size() - nr; i++) {
            s.append(packages.get(i)).append("/");
        }
        return s.toString();
    }

    /**
     * Funkcja usuwa od lewej strony ilość package wprowadzonych jako parametr oraz dodaje nową część
     * i zwraca wynikową ścieżkę.
     *
     * @param nr   Ilość package - licząc od końca
     * @param path Nowa częśc która zostanie dodana na końcu po usunięciu wskazanej ilości package
     *             * @return Nowa scieżka
     */
    public String path(int nr, String path) {
//        StringBuilder s = new StringBuilder("jar:file:/"); //todo zmienić na tą linie, gdy będzie build do pliku .jar
        StringBuilder s = new StringBuilder("file:/");
        for (int i = 0; i < packages.size() - nr; i++) {
            s.append(packages.get(i)).append("/");
        }
        return s.toString() + path;
    }

    /**
     * Zwraca nazwę dysku.
     * @return
     */
    public String disk()
    {
        return packages.get(0)+"\\";
    }


    /**
     * Zwraca nazwy packages gotowe do wypisania.
     * @return Nazwy packages.
     */
    public String printPackages()
    {
        StringBuilder sb = new StringBuilder("\n");
        int a = 1;

        for(String s : packages)
        {
            sb.append(a).append(". ").append(s);
            a++;
        }
        return sb.toString();
    }
}
