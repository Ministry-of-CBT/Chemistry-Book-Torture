import globals.Globals;

BR = recipemap('batch_reactor')
CSTR = recipemap('continuous_stirred_tank_reactor')
DISTILLERY = recipemap('distillery')
DISTILLATION_TOWER = recipemap('distillation_tower')
ROASTER = recipemap('roaster')
MACERATOR = recipemap('macerator')
SIFTER = recipemap('sifter')
CRYSTALLIZER = recipemap('crystallizer')
REACTION_FURNACE = recipemap('reaction_furnace')
GRAVITY_SEPARATOR = recipemap('gravity_separator')
FF = recipemap('froth_flotation')
CLARIFIER = recipemap('clarifier')
MIXER = recipemap('mixer')
EBF = recipemap('electric_blast_furnace')
ELECTROMAGNETIC_SEPARATOR = recipemap('electromagnetic_separator')
AUTOCLAVE = recipemap('autoclave')
ELECTROLYTIC_CELL = recipemap('electrolytic_cell')
SIFTER = recipemap('sifter')
DRYER = recipemap('dryer')
VACUUM_DT = recipemap('vacuum_distillation')
BCR = recipemap('bubble_column_reactor')
SIEVE_DT = recipemap('sieve_distillation')
PHASE_SEPARATOR = recipemap('phase_separator')
VACUUM_CHAMBER = recipemap('vacuum_chamber')

//The pelletized material is smelted in a shaft furnace to form a 
//copper – nickel matte. Oxygen is then blown into the converter
//to oxidize the iron sulfide selectively to iron oxide, 
//which forms a slag.

EBF.recipeBuilder()
        .circuitMeta(2)
        .inputs(ore('dustFlotatedPentlandite') * 8)
        .outputs(metaitem('dustGreenMatte') * 8)
        .outputs(metaitem('dustGraniteTailings'))
        .duration(20)
        .blastFurnaceTemp(2700)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

EBF.recipeBuilder()
        .inputs(ore('dustGreenMatte') * 8)
        .fluidInputs(fluid('oxygen') * 4000)
        .outputs(metaitem('white_matte') * 8)
        .outputs(metaitem('dustIronIiiOxide') * 2)
        .fluidOutputs(fluid('sulfur_dioxide') * 2000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(metaitem('white_matte'))
        .outputs(metaitem('dustWhiteMatte'))
        .duration(100)
        .EUt(2)
        .buildAndRegister()

//method described in https://patentimages.storage.googleapis.com/2b/70/5a/cbb5549831857c/US4571262.pdf

//atmospheric leach
BR.recipeBuilder()
        .inputs(ore('dustWhiteMatte') * 2)
        .fluidInputs(fluid('air') * 9750)
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidOutputs(fluid('oxidized_pgm_leach') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustWhiteMatte'))
        .fluidInputs(fluid('air') * 9750)
        .fluidInputs(fluid('acidic_nickel_copper_sulfate_solution') * 1000)
        .fluidOutputs(fluid('oxidized_pgm_leach') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

SIFTER.recipeBuilder()
        .fluidInputs(fluid('oxidized_pgm_leach') * 1000)
        .notConsumable(metaitem('item_filter'))
        .fluidOutputs(fluid('impure_nickel_sulfate') * 1000)
        .outputs(metaitem('dustCopperRichPgmSolids'))
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//pressure leach
AUTOCLAVE.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidInputs(fluid('oxygen') * 1000)
        .inputs(ore('dustCopperRichPgmSolids'))
        .fluidOutputs(fluid('pgm_copper_leach_slurry') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

AUTOCLAVE.recipeBuilder()
        .fluidInputs(fluid('sulfuric_acid') * 1500)
        .fluidInputs(fluid('oxygen') * 1500)
        .inputs(ore('dustSeleniumFreeCalcine'))
        .fluidOutputs(fluid('pgm_copper_leach_slurry') * 1500)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

SIFTER.recipeBuilder()
        .fluidInputs(fluid('pgm_copper_leach_slurry') * 1000)
        .notConsumable(metaitem('item_filter'))
        .fluidOutputs(fluid('pgm_free_copper_leach') * 1000)
        .outputs(metaitem('dustPgmConcentrate'))
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//selenium extraction
CSTR.recipeBuilder()
        .fluidInputs(fluid('pgm_free_copper_leach') * 50)
        .fluidInputs(fluid('sulfur_dioxide') * 50)
        .fluidOutputs(fluid('sulfidic_copper_leach') * 50)
        .duration(50)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

SIFTER.recipeBuilder()
        .fluidInputs(fluid('sulfidic_copper_leach') * 1000)
        .notConsumable(metaitem('item_filter'))
        .fluidOutputs(fluid('selenium_free_copper_leach') * 1000)
        .outputs(metaitem('dustCopperSelenidePrecipitate'))
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister() 

ROASTER.recipeBuilder()
        .inputs(ore('dustCopperSelenidePrecipitate'))
        .fluidInputs(fluid('air') * 2000)
        .chancedOutput(metaitem('dustSeleniumDioxide'), 2000, 0)
        .outputs(metaitem('dustSeleniumFreeCalcine'))
        .duration(300)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

ROASTER.recipeBuilder()
        .inputs(ore('dustCopperSelenidePrecipitate'))
        .fluidInputs(fluid('oxygen') * 419)
        .chancedOutput(metaitem('dustSeleniumDioxide'), 2095, 0)
        .outputs(metaitem('dustSeleniumFreeCalcine'))
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//copper electrowinning
ELECTROLYTIC_CELL.recipeBuilder()
        .fluidInputs(fluid('selenium_free_copper_leach') * 1000)
        .inputs(ore('plateCopper'))
        .notConsumable(ore('plateStainlessSteel'))
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidOutputs(fluid('acidic_nickel_copper_sulfate_solution') * 2000)
        .outputs(metaitem('dustCopper') * 2)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()    

//cyanex production

BR.recipeBuilder()
        .inputs(ore('dustSodiumHypophosphite') * 3)
        .fluidInputs(fluid('acetic_acid') * 1000)
        .fluidOutputs(fluid('sodium_hypophosphite_solution') * 1000) //material
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//diisobutylene production
// https://patents.google.com/patent/US7414164B2/en
// mostly from this patent: https://patents.google.com/patent/US5877372A/en
BR.recipeBuilder()
        //.notConsumable(ore('dustAluminiumSilicate'))
        .notConsumable(metaitem('beads.ag_fifty_w_x_eight')) 
        //dry sulfonic acid ion exchange resin like Amberlyst 15 or zeolite-based catalysts
        .notConsumable(fluid('tert_butyl_alcohol') * 100) //1 to 30 wt %
        .notConsumable(fluid('isooctane') * 100) //diluent, 90% isooctane and 10% isododecane/2,2,4,4,6 pentamethyl heptane
        .fluidInputs(fluid('isobutylene') * 1000)
        .fluidOutputs(fluid('diisobutylene_mixture') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder() //90% yield converted to diisobutytlene?
        .fluidInputs(fluid('diisobutylene_mixture') * 1000)
        .fluidOutputs(fluid('isobutylene') * 90)
        .fluidOutputs(fluid('diisobutylene') * 400)
        .fluidOutputs(fluid('triisobutylene') * 20)
//        ↓ this is not doable currently due to DT input restrictions
//        .notConsumable(fluid('tert_butyl_alcohol') * 50) //not all tBuOH and isobutent reacts in the dimerization process
        .duration(800)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//source for trimethylpentylphosphinic acid WO2013083047A1
BR.recipeBuilder()
        .notConsumable(metaitem('emitter.lv'))
        .fluidInputs(fluid('sodium_hypophosphite_solution') * 1000)
        .fluidInputs(fluid('acetone') * 100)
        .fluidInputs(fluid('diisobutylene') * 500) //material
        .fluidOutputs(fluid('crude_trimethylpentylphosphinic_acid') * 1000) //material
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister();


CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('crude_trimethylpentylphosphinic_acid') * 1000)
        .fluidInputs(fluid('sodium_hydroxide_solution') * 1000)
        .fluidOutputs(fluid('alkaline_trimethylpentylphosphinic_acid') * 1000) //material
        .fluidOutputs(fluid('diluted_sodium_hydroxide_solution') * 1000)
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister();

MIXER.recipeBuilder()
        .fluidInputs(fluid('alkaline_trimethylpentylphosphinic_acid') * 1000)
        .fluidInputs(fluid('sulfuric_acid') * 1000)
        .fluidOutputs(fluid('acidified_trimethylpentylphosphinic_acid') * 1000) //material
        .fluidOutputs(fluid('diluted_sulfuric_acid') * 1000) 
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister();

DRYER.recipeBuilder()
        .fluidInputs(fluid('acidified_trimethylpentylphosphinic_acid') * 1000)
        .fluidOutputs(fluid('dehydrated_trimethylpentylphosphinic_acid') * 1000) //material
        .EUt(Globals.voltAmps[2])
        .duration(600)
        .buildAndRegister()

VACUUM_DT.recipeBuilder()
        .fluidInputs(fluid('dehydrated_trimethylpentylphosphinic_acid') * 1000)
        .fluidOutputs(fluid('trimethylpentylphosphinic_acid') * 1000) //new material
        .duration(120)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('trimethylpentylphosphinic_acid') * 1000)
        .fluidInputs(fluid('ortho_xylene') * 1000)
        .fluidOutputs(fluid('cobalt_extraction_mixture') * 1000)
        .duration(120)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()
//end cyanex chain


//input the impure nickel sulfate solution into a crystallizer
//it should make a mixture of cobalt and nickel sulfate dusts and a mother liquor of iron ii sulfate solution
//then redissolve and extract with cyanex

// crystallisation step makes a nickel-cobalt solution and leaves behind an iron sulfate mother liquor
CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('dense_steam') * 1000)
        .fluidInputs(fluid('impure_nickel_sulfate') * 1000)
        .fluidOutputs(fluid('iron_sulfate_mother_liquor') * 1000)
        .outputs(metaitem('dustNickelCobaltSulfate') * 6)
        .duration(400)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLERY.recipeBuilder()
        .fluidInputs(fluid('iron_sulfate_mother_liquor') * 1000)
        .fluidOutputs(fluid('water') * 1000)
        .outputs(metaitem('dustIronSulfate') * 7)
        .duration(80)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(ore('dustNickelCobaltSulfate') * 6)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('nickel_cobalt_sulfate_solution') * 1000)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// cyanex-272 extractant makes cobalt sulfate leaving behind nickel sulfate solution
CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('cobalt_extraction_mixture') * 1000) 
        .fluidInputs(fluid('nickel_cobalt_sulfate_solution') * 1000)
        .fluidOutputs(fluid('cobalt_extract') * 1200) 
        .fluidOutputs(fluid('nickel_sulfate_solution') * 800) 
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('cobalt_extract') * 1200)
        .fluidInputs(fluid('diluted_sulfuric_acid') * 10)
        .fluidOutputs(fluid('cobalt_extraction_mixture') * 1000)
        .fluidOutputs(fluid('cobalt_sulfate_solution') * 200) 
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()
