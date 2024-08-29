package com.kata.pedidos.domain;

public enum Produtos {
    PIZZA_CALABRESA("PIZZA CALABRESA"), X_SALADA("X_SALADA"), CX_SKOL("Cx Skol"),
    X_BACON("X_BACON");

    private final String nome;

	Produtos(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return String.format("%s", nome);
    }
}
