import static globals.Globals.*

ROASTER = recipemap('roaster')
CENTRIFUGE = recipemap('centrifuge')
AUTOCLAVE = recipemap('autoclave')
BR = recipemap('batch_reactor')
EBF = recipemap('electric_blast_furnace')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
BCR = recipemap('bubble_column_reactor')
DISTILLERY = recipemap('distillery')
EMSEPARATOR = recipemap('electromagnetic_separator')
ELECTROLYZER = recipemap('electrolyzer')
CRYSTALLIZER = recipemap('crystallizer')

// Aluminium Nugget * 3
mods.gregtech.electric_blast_furnace.removeByInput(100, [metaitem('dustRuby')], null)
//Remove EBF recipe with Aluminium Dust:
mods.gregtech.electric_blast_furnace.removeByInput(120, [metaitem('dustAluminium'), metaitem('circuit.integrated').withNbt(["Configuration": 2])], [fluid('nitrogen') * 1000])
mods.gregtech.electric_blast_furnace.removeByInput(120, [metaitem('dustAluminium'), metaitem('circuit.integrated').withNbt(["Configuration": 1])], null)
//EBF recipe with Al dust re-enabled:
EBF.recipeBuilder()
		.inputs(ore('dustAluminium'))
		.outputs(metaitem('ingotAluminium'))
		.duration(240)
		.blastFurnaceTemp(933)
		.EUt(120)
		.buildAndRegister()



// Ruby Slurry * 3000
mods.gregtech.mixer.removeByInput(1920, [metaitem('dustRuby') * 6], [fluid('aqua_regia') * 3000])

// Bauxite -> Red Mud

ROASTER.recipeBuilder()
.fluidInputs(fluid('sodium_hydroxide_solution') * 2000)
.inputs(ore('dustBauxite') * 5)
.fluidOutputs(fluid('impure_sodium_aluminate_solution') * 3000)
.duration(200)
.EUt(20)
.buildAndRegister()

CENTRIFUGE.recipeBuilder()
.fluidInputs(fluid('impure_sodium_aluminate_solution') * 3000)
.fluidOutputs(fluid('sodium_aluminate_solution') * 3000)
.fluidOutputs(fluid('red_mud') * 500)
.duration(200)
.EUt(20)
.buildAndRegister()

// Carbon dioxide bubbling

CRYSTALLIZER.recipeBuilder()
.fluidInputs(fluid('sodium_aluminate_solution') * 3000)
.fluidInputs(fluid('water') * 1000)
.fluidInputs(fluid('carbon_dioxide') * 1000)
.fluidOutputs(fluid('impure_soda_ash_solution') * 1000)
.outputs(metaitem('dustAluminiumHydroxide') * 14)
.duration(300)
.EUt(20)
.buildAndRegister()

CRYSTALLIZER.recipeBuilder()
.fluidInputs(fluid('sodium_aluminate_solution') * 1500)
.fluidInputs(fluid('water') * 1500)
.notConsumable(ore('dustAluminiumHydroxide'))
.fluidOutputs(fluid('impure_sodium_hydroxide_solution') * 1000) //TO GALLIUM PROCESSING
.outputs(metaitem('dustAluminiumHydroxide') * 7)
.duration(300)
.EUt(20)
.buildAndRegister()

// Alumina from Al(OH)3

EBF.recipeBuilder()
.inputs(ore('dustAluminiumHydroxide') * 14)
.fluidOutputs(fluid('steam') * 3000)
.outputs(metaitem('dustAlumina') * 5)
.duration(100)
.blastFurnaceTemp(1400)
.EUt(40)
.buildAndRegister()

// Electrolysis

ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(fluid('cryolite') * 2592)
        .inputs(ore('dustAlumina') * 10)
        .inputs(ore('dustAluminiumTrifluoride'))
        .inputs(ore('dustCoke') * 3)
        .fluidOutputs(fluid('hydrogen_fluoride') * 750)
        .fluidOutputs(fluid('carbon_dioxide') * 3000)
        .outputs(metaitem('ingotAluminium') * 4)
        .duration(300)
        .EUt(40)
        .buildAndRegister()

ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(fluid('cryolite') * 2592)
        .inputs(ore('dustAlumina') * 10)
        .inputs(ore('dustAluminiumTrifluoride'))
        .inputs(ore('dustCarbon') * 3)
        .fluidOutputs(fluid('hydrogen_fluoride') * 750)
        .fluidOutputs(fluid('carbon_dioxide') * 3000)
        .outputs(metaitem('ingotAluminium') * 4)
        .duration(100)
        .EUt(40)
        .buildAndRegister()

// EBF
def combustibles = Globals.combustibles

for (combustible in combustibles) {
        EBF.recipeBuilder()
        .inputs(ore('dustAlumina') * 10)
        .inputs(ore(combustible.name) * combustible.amount_required * 3)
        .fluidOutputs(fluid('carbon_dioxide') * 3000)
        .outputs(metaitem('ingotAluminium') * 4)
        .blastFurnaceTemp(1200)
        .duration(60)
        .EUt(Globals.voltAmps[3] * 2)
        .buildAndRegister()
}

// Production of cryolite

ROASTER.recipeBuilder()
.fluidInputs(fluid('hydrofluoric_acid') * 6000)
.inputs(ore('dustAlumina') * 5)
.fluidOutputs(fluid('steam') * 9000)
.outputs(metaitem('dustAluminiumTrifluoride') * 8)
.duration(300)
.EUt(20)
.buildAndRegister()

BCR.recipeBuilder()
.fluidInputs(fluid('hydrogen_fluoride') * 50)
.fluidInputs(fluid('sodium_hydroxide_solution') * 50)
.fluidOutputs(fluid('sodium_fluoride_solution') * 50)
.duration(5)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

DISTILLERY.recipeBuilder()
.fluidInputs(fluid('sodium_fluoride_solution') * 1000)
.fluidOutputs(fluid('water') * 1000)
.outputs(metaitem('dustSodiumFluoride') * 2)
.duration(200)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

ROASTER.recipeBuilder()
.inputs(ore('dustAluminiumTrifluoride') * 4)
.inputs(ore('dustSodiumFluoride') * 6)
.outputs(metaitem('dustCryolite') * 10)
.duration(180)
.EUt(Globals.voltAmps[1])
.buildAndRegister()

// Red mud processing

EMSEPARATOR.recipeBuilder()
.fluidInputs(fluid('red_mud') * 2000)
.chancedOutput(metaitem('dustIronIiiOxide'), 5000, 0)
.fluidOutputs(fluid('concentrated_red_mud') * 1000)
.duration(200)
.EUt(Globals.voltAmps[2])
.buildAndRegister()

EBF.recipeBuilder()
.fluidInputs(fluid('concentrated_red_mud') * 2000)
.outputs(ore('ingotIron').first())
.outputs(metaitem('red_mud_slag'))
.blastFurnaceTemp(1600)
.duration(300)
.EUt(Globals.voltAmps[2] * 2)
.buildAndRegister()

CENTRIFUGE.recipeBuilder()
.fluidInputs(fluid('sulfuric_acid') * 250)
.inputs(metaitem('red_mud_slag'))
.outputs(metaitem('dustTinyRutile') * 3)
.outputs(metaitem('leached_red_mud_slag'))
.duration(100)
.EUt(Globals.voltAmps[3])
.buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 3000)
        .inputs(ore('dustAluminiumHydroxide') * 14)
        .fluidOutputs(fluid('aluminium_sulfate_solution') * 1000)
        .duration(100)
        .EUt(120)
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('aluminium_sulfate_solution') * 1000)
        .outputs(metaitem('dustAluminiumSulfate') * 17)
        .fluidOutputs(fluid('water') * 6000)
        .duration(100)
        .EUt(120)
        .buildAndRegister()

ROASTER.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 3000)
        .inputs(ore('dustAluminium') * 2)
        .outputs(metaitem('dustAluminiumSulfate') * 17)
        .fluidOutputs(fluid('hydrogen') * 6000)
        .duration(100)
        .EUt(120)
        .buildAndRegister()

// CENTRIFUGE.recipeBuilder()
// .fluidInputs(fluid('hydrochloric_acid') * 500)
// .inputs(metaitem('leached_red_mud_slag'))
// .fluidOutputs(fluid('acidic_ree_solution') * 500)
// .duration(100)
// .EUt(Globals.voltAmps[4])
// .buildAndRegister()

