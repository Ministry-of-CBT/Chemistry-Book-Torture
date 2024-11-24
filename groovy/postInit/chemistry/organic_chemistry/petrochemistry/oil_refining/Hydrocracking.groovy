import globals.Globals
import static globals.Petrochemistry.*

CRACKER = recipemap('cracker')

crackables.each { _, crackable ->
    if (crackable.hydro_crackable) {

        CRACKER.recipeBuilder()
            .fluidInputs(crackable.get(1000))
            .fluidInputs(fluid('hot_hp_hydrogen') * 1000)
            .fluidOutputs(crackable.getHydro(1000))
            .duration(80)
            .EUt(Globals.voltAmps[2])
            .buildAndRegister()  
        
    }
}