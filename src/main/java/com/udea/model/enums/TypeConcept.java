package com.udea.model.enums;

/**
 * TypeConcept
 * @author Antonio
 */
public enum TypeConcept {
    CREDITO("credito"),
    DEBITO("debito");
    
    public final String label;

    private TypeConcept(String label) {
        this.label = label;
    }
}
