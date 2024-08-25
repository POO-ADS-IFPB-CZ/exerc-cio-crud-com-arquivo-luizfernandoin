package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class PessoaDao {
    private Set<Pessoa> pessoas;
    private final String filename = "pessoas.dat";

    public PessoaDao() {
        pessoas = loadFromFile();
    }

    public boolean addPessoa(Pessoa pessoa) {
        if (pessoas.add(pessoa)) {
            saveToFile();
            return true;
        } else {
            return false;
        }
    }

    public Set<Pessoa> getPessoas() {
        return pessoas;
    }

    public boolean removePessoa(String email) {
        boolean removed = pessoas.removeIf(p -> p.getEmail().equals(email));
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    @SuppressWarnings("unchecked")
    private Set<Pessoa> loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (Set<Pessoa>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new HashSet<>();
        }
    }

    private void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(pessoas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
