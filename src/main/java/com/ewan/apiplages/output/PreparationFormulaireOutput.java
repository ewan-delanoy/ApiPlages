package com.ewan.apiplages.output;

import java.util.List;

public class PreparationFormulaireOutput {

    private final List<EmplacementOutput> emplacements;
    private final List<EquipementOutput> equipements;
    private final List<LienDeParenteOutput> liensDeParente;
    private final List<PaysOutput> paysEnvisageables;

    public PreparationFormulaireOutput(List<EmplacementOutput> emplacements, List<EquipementOutput> equipements, List<LienDeParenteOutput> liensDeParente, List<PaysOutput> paysEnvisageables) {
        this.emplacements = emplacements ;
        this.equipements = equipements ;
        this.liensDeParente = liensDeParente ;
        this.paysEnvisageables = paysEnvisageables;
    }

    public List<EmplacementOutput> getEmplacements() { return this.emplacements; }
    public List<EquipementOutput> getEquipements() { return this.equipements; }
    public List<LienDeParenteOutput> getLiensDeParente() {return this.liensDeParente; }
    public List<PaysOutput> getPaysEnvisageables () {return this.paysEnvisageables; }

}
