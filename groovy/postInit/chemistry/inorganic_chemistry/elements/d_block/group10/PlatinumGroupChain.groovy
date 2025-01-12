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

//LOW YIELD CHAIN FROM SECONDARY ORES
MACERATOR.recipeBuilder()
        .inputs(item('susy:resource_block', 10))
        .outputs(metaitem('dustAlluvialPgmSand') * 9)
        .duration(160)
        .EUt(30)
        .buildAndRegister()

SIFTER.recipeBuilder()
        .inputs(ore('dustAlluvialPgmSand'))
        .outputs(metaitem('nuggetAlluvialPgm') * 4)
        .outputs(metaitem('dustNetherQuartz') * 3)
        .duration(160)
        .EUt(30)
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustAlluvialPgm') * 2)
        .fluidInputs(fluid('aqua_regia') * 12000)
        .fluidInputs(fluid('nitric_acid') * 1000)
        .fluidOutputs(fluid('alluvial_pgm_solution') * 5000)
        .fluidOutputs(fluid('hydrogen') * 1000)
        .fluidOutputs(fluid('nitrogen_dioxide') * 5000)
        .duration(120)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

BR.recipeBuilder()
        .inputs(ore('dustAnyPurityZinc'))
        .fluidInputs(fluid('alluvial_pgm_solution') * 10000)
        .outputs(metaitem('dustIronIiChloride') * 3)
        .fluidOutputs(fluid('cemented_alluvial_pgm_solution') * 10000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder() // 2 H2PtCl6 + 4 NH4Cl --> 2 (NH4)2PtCl6 + 4 HCl
        .circuitMeta(1)
        .fluidInputs(fluid('cemented_alluvial_pgm_solution') * 10000)  // 67% (NH4)2Pt, 33% H2PdCl4
        .fluidInputs(fluid('ammonium_chloride_solution') * 4000)
        .chancedOutput(metaitem('dustAmmoniumHexachloroplatinate') * 34, 7500, 0)
        .fluidOutputs(fluid('alluvial_platinum_mother_liquor') * 14000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('alluvial_platinum_mother_liquor') * 14000)
        .chancedOutput(metaitem('dustZincChloride') * 3, 9000, 0)
        .fluidOutputs(fluid('ammonium_chloride_solution') * 1000)
        .fluidOutputs(fluid('water') * 13000)
        .fluidOutputs(fluid('hydrogen_chloride') * 4000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

REACTION_FURNACE.recipeBuilder()
        .inputs(ore('dustAmmoniumHexachloroplatinate') * 17)
        .fluidInputs(fluid('hydrogen') * 4000)
        .outputs(metaitem('sponge.platinum'))
        .fluidOutputs(fluid('ammonia') * 2000)
        .fluidOutputs(fluid('hydrogen_chloride') * 6000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(metaitem('sponge.platinum'))
        .outputs(metaitem('dustPlatinum'))
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

BR.recipeBuilder()
        .notConsumable(ore('springCupronickel'))
        .fluidInputs(fluid('alluvial_platinum_mother_liquor') * 14000)
        .fluidOutputs(fluid('alluvial_divalent_palladium_solution') * 14000)
        .fluidOutputs(fluid('chlorine') * 50)
        .duration(120)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('ammonia_solution') * 2000)
        .fluidInputs(fluid('alluvial_divalent_palladium_solution') * 14000) //(H2PdCl4)(ZnCl2)(HCl)4(H2O)14
        .chancedOutput(metaitem('dustDiamminedichloropalladium') * 11, 7500, 0)
        .fluidOutputs(fluid('alluvial_palladium_mother_liquor') * 16000) //(NH4Cl)2(ZnCl2)(HCl)4(H2O)16
        .duration(120)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

REACTION_FURNACE.recipeBuilder()
        .inputs(ore('dustDiamminedichloropalladium') * 11)
        .fluidInputs(fluid('hydrogen') * 4000)
        .outputs(metaitem('dustPalladium'))
        .fluidOutputs(fluid('ammonia') * 2000)
        .fluidOutputs(fluid('hydrogen_chloride') * 2000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('alluvial_palladium_mother_liquor') * 16000)
        .chancedOutput(metaitem('dustZincChloride') * 3, 9500, 0)
        .fluidOutputs(fluid('ammonium_chloride_solution') * 2000)
        .fluidOutputs(fluid('water') * 14000)
        .fluidOutputs(fluid('hydrogen_chloride') * 4000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//MODERN SEPARATION PROCESSES
GRAVITY_SEPARATOR.recipeBuilder() 
        .inputs(ore('dustPentlandite'))
        .outputs(metaitem('dustSiftedPentlandite'))
        .chancedOutput(metaitem('dustUltramaficTailings'), 2500, 0)
        .EUt(Globals.voltAmps[1])
        .duration(40)
        .buildAndRegister()

MIXER.recipeBuilder()
        .inputs(ore('dustSiftedPentlandite') * 8)
        .fluidInputs(fluid('distilled_water') * 2000)
        .fluidOutputs(fluid('impure_pentlandite_slurry') * 2000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

FF.recipeBuilder()
        .fluidInputs(fluid('impure_pentlandite_slurry') * 2000)
        .notConsumable(ore('dustAluminiumNitride'))
        .notConsumable(ore('dustPotassiumOctylHydroxamate'))
        .notConsumable(fluid('tnt_slurry') * 100)
        .fluidOutputs(fluid('pentlandite_slurry') * 1000)
        .fluidOutputs(fluid('ultramafic_tailing_slurry') * 1000)
        .EUt(Globals.voltAmps[3])
        .duration(80)
        .buildAndRegister()

CLARIFIER.recipeBuilder()
        .fluidInputs(fluid('pentlandite_slurry') * 1000)
        .outputs(metaitem('dustFlotatedPentlandite') * 16)
        .fluidOutputs(fluid('wastewater') * 1000)
        .duration(20)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

//The pelletized material is smelted in a shaft furnace to form a 
//copper – nickel matte. Oxygen is then blown into the converter
//to oxidize the iron sulfide selectively to iron oxide, 
//which forms a slag.
EBF.recipeBuilder()
        .inputs(ore('dustFlotatedPentlandite') * 8)
        .outputs(metaitem('dustGreenMatte') * 8)
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
        .notConsumable(fluid('tert_butyl_alcohol') * 1) //1 to 30 wt %
        .notConsumable(fluid('isooctane') * 1) //diluent, 90% isooctane and 10% isododecane/2,2,4,4,6 pentamethyl heptane
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

//TODO For Pre-PGM Proc: 
//-IronSulfateMotherLiquor Proc (me personally, I would precipitate it as a hydroxide or pressure autoclave in oxygen atmosphere to produce an iron oxide hydroxide is simpler to implement though)
//-Cobalt sulfate (is it a solution (probably, so I should update the formula for it))
//-Maybe add a formula to cobalt extraction mixture, it's made from C8H10 + C16H35O2P

//method described in original paper (renner 2001)

// ELECTROMAGNETIC_SEPARATOR.recipeBuilder()
//         .inputs(ore('dustWhiteMatte'))
//         .outputs(metaitem('pgm_RichMatte'))
//         .outputs(metaitem('pgm_FreeMatte'))
//         .duration(20)
//         .EUt(Globals.voltAmps[2])
//         .buildAndRegister()

// AUTOCLAVE.recipeBuilder()
//         .inputs(ore('pgm_RichMatte'))
//         .fluidInputs(fluid('oxygen'))
//         .fluidInputs(fluid('sulfuric_acid'))
//         .outputs(fluid('dustPgm_concentrate'))
//         .fluidOutputs(fluid(''))
//         .duration(200)
//         .EUt(Globals.voltAmps[2])



// PGM PROCESSING Tier 2

//Hydrochloric Acid solution
//Output: Solution Pt,Rh,Ir,Au,Ag
BCR.recipeBuilder()
        .fluidInputs(fluid('chlorine') * 1000)
        .fluidInputs(fluid('hydrochloric_acid') * 1000)
        .fluidInputs(fluid('chlorinated_pgm_concentrate') * 1000)
        .fluidOutputs(fluid('pgm_solution') * 1000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//OSMIUM

//a. contacting the mixture with an oxidizing solution (NaClO3) to form a volatile RuO4/OsO4 vapor; 
//In acidic media, osmium is oxidized by peroxodisulfate to form OsO4
//b. bubbling the OsO4 vapor through a KOH trapping solution to form an amount of K2[OsO4(OH)2] 
//dissolved in the KOH trapping solution;
//c. contacting the dissolved K2[OsO4(OH)2] with a reducing agent (ethanol) to form an Os precipitate
//d. separating the Os precipitate from the KOH trapping solution
//adsorb oso4 distillate into koh solution (buble column)
//precipitate with ethanol

// 8% ruthenium and 1% osmium
CSTR.recipeBuilder()
        .fluidInputs(fluid('pgm_solution') * 100)
        .fluidInputs(fluid('sodium_chlorate_solution') * 9)
        .fluidOutputs(fluid('chlorate_treated_pgm_solution') * 100)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('chlorate_treated_pgm_solution') * 1000)
        .fluidOutputs(fluid('os_ru_tetroxide_mixture') * 90)
        .fluidOutputs(fluid('os_ru_free_pgm_solution') * 910)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// OsO4 + 8RuO4 + 98HCl(H2O) -> H2OsCl6 + 8H3RuCl6 + 134H2O + 22Cl2
// All of the fun byproducts are pretended to just stay in the Ru solution
// Also technically we should have hexachlororuthenic(III, IV) acid, but that's too complicated, so it's just Ru(IV)
CSTR.recipeBuilder()
        .fluidInputs(fluid('os_ru_tetroxide_mixture') * 9)
        .fluidInputs(fluid('hydrochloric_acid') * 98)
        .fluidOutputs(fluid('os_ru_solution') * 143)
        .fluidOutputs(fluid('chlorine') * 22)
        .duration(6)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// Distill out with hydrogen peroxide
// Base: H2OsCl6 + 5H2O2 -> OsO4 + 3Cl2 + 6H2O
// H2OsCl6 + 8H3RuCl6 + 134H2O + 5H2O2 -> OsO4 + 8H3RuCl6 + 140H2O + 3Cl2
DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('os_ru_solution') * 1430)
        .fluidInputs(fluid('hydrogen_peroxide_solution') * 50)
        .fluidOutputs(fluid('hexachlororuthenic_acid_solution') * 1480)
        .fluidOutputs(fluid('osmium_tetroxide') * 10)
        .fluidOutputs(fluid('chlorine') * 30)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// 8H2RuCl6 + 140H2O + 16NH3 -> 8(NH4)2RuCl6 + 140H2O
// (Planet, is this correct?)
BCR.recipeBuilder()
        .fluidInputs(fluid('hexachlororuthenic_acid_solution') * 1480)
        .fluidInputs(fluid('ammonia') * 160)
        .fluidOutputs(fluid('ammonium_hexachlororuthenate_solution') * 1480)
        .duration(60)
        .EUt(Globals.voltAmps[1])
        .buildAndRegister()

// 8(NH4)2RuCl6 + 140H2O + 32H -> 16NH4Cl + 8Ru + 140H2O + 32HCl
// 2(NH4)2RuCl6 + 35H2O + 8H -> 4NH4Cl + 2Ru + 35H2O + 8HCl
// The input is 370
REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('ammonium_hexachlororuthenate_solution') * 370)
        .fluidInputs(fluid('hydrogen') * 80)
        .chancedOutput(metaitem('sponge.ruthenium') * 2, 100, 0)
        .chancedOutput(metaitem('dustAmmoniumChloride') * 24, 100, 0)
        .fluidOutputs(fluid('dense_steam') * 350)
        .fluidOutputs(fluid('hydrogen_chloride') * 80)
        .duration(500)
        .EUt(Globals.voltAmps[3])

MACERATOR.recipeBuilder()
        .inputs(metaitem('sponge.ruthenium'))
        .outputs(metaitem('dustRuthenium'))
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//2OsO4 + C2H5OH + 5KOH -> CH3CO2K + 2K2[OsO2(OH)4]
BCR.recipeBuilder()
        .fluidInputs(fluid('osmium_tetroxide') * 2000)
        .fluidInputs(fluid('potassium_hydroxide_solution') * 5000)
        .fluidInputs(fluid('ethanol') * 1000)
        .outputs(metaitem('dustPotassiumOsmate') * 26) 
        .fluidOutputs(fluid('dilute_potassium_acetate_solution') * 5000)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('dilute_potassium_acetate_solution') * 5000)
        .fluidOutputs(fluid('potassium_acetate_solution') * 2000)
        .fluidOutputs(fluid('water') * 3000)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()


// 2CH3COOK + 2H2O → C2H6 + 2CO2 + 2KOH + H2
ELECTROLYTIC_CELL.recipeBuilder()
        .notConsumable(ore('rodPlatinum') * 2)
        .fluidInputs(fluid('potassium_acetate_solution') * 2000)
        .fluidInputs(fluid('water') * 2000)
        .fluidOutputs(fluid('ethane') * 1000)
        .fluidOutputs(fluid('carbon_dioxide') * 2000)
        .fluidOutputs(fluid('potassium_hydroxide_solution') * 2000)
        .fluidOutputs(fluid('hydrogen') * 2000)
        .EUt(Globals.voltAmps[2])
        .duration(100)
        .buildAndRegister()

//K2[OsO2(OH)4] + 3H2 → Os + 2KOH + 4H2O
ROASTER.recipeBuilder()
        .fluidInputs(fluid('potassium_osmate_solution') * 1000)
        .fluidInputs(fluid('hydrogen') * 3000)
        .outputs(metaitem('sponge.osmium'))
        .fluidOutputs(fluid('potassium_hydroxide_solution') * 2000)
        .fluidOutputs(fluid('water') * 4000)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(metaitem('sponge.osmium'))
        .outputs(metaitem('dustOsmium'))
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//SILVER

//Distillative removal of HCl
//Dilution with water
//Output: AgCl precipitate
//Output: Solution Pt,Pd,Rh,Ir,Au

DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('os_ru_free_pgm_solution') * 910)
        .outputs(metaitem('dustSilverChloride') * 2)
        .fluidOutputs(fluid('silver_free_pgm_solution') * 890)
        .fluidOutputs(fluid('hydrogen_chloride'))
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//GOLD

//Liquid-liquid extraction
//stripping
//Output: H(AuCl4) extract
//Output: Raffinate Pt,Pd,Rh,Ir,Ru

//extraction with dibutyl carbitol
CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('silver_free_pgm_solution') * 890)
        .fluidInputs(fluid('dibutyl_carbitol') * 10)
        .fluidOutputs(fluid('gold_extract') * 10)
        .fluidOutputs(fluid('gold_free_pgm_solution') * 880)
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//scrubbing gold extract
CENTRIFUGE.recipeBuilder() 
        .fluidInputs(fluid('gold_extract') * 1000)
        .notConsumable(fluid('diluted_hydrochloric_acid'))
        .fluidOutputs(fluid('scrubbed_gold_extract') * 1000)
        .EUt(240)
        .duration(120)
        .buildAndRegister()

//stripping with oxalic acid
//2HAuCl4 + 3C2H2O4 -> 2Au + 8HCl + 6CO2
CENTRIFUGE.recipeBuilder() 
        .fluidInputs(fluid('scrubbed_gold_extract') * 2000)
        .fluidInputs(fluid('oxalic_acid_solution') * 3000)
        .fluidOutputs(fluid('dibutyl_carbitol') * 1000)
        .fluidOutputs(fluid('hydrogen_chloride') * 8000)
        .fluidOutputs(fluid('carbon_dioxide') * 6000)
        .outputs(metaitem('dustGold') * 2) //needs to be calcinated
        .EUt(240)
        .duration(120)
        .buildAndRegister()


//PLATINUM

//Oxidation with Cl2
//Reduction of Ir(IV) to Ir(III)
//Precipitation with NH4Cl
//Output: (NH4)2(PtCl6) precipitate
//Output: Solution Pd,Rh,Ir

//solution + cl2 + h2c2o4*h2o -> solution with shit + co2
//solution with shit + nh4cl*h2o -> (nh4)2[ptcl6] + platinum-free pgm solution
//(NH4)2[PtCl6] + 2H2 --350*c-> Pt + 2NH4Cl + 4HCl

BR.recipeBuilder()
        .fluidInputs(fluid('gold_free_pgm_solution') * 880)
        .fluidInputs(fluid('oxalic_acid_solution') * 10)
        .fluidOutputs(fluid('reduced_gold_free_pgm_solution') * 880)
        .fluidOutputs(fluid('carbon_dioxide') * 20)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

BR.recipeBuilder()
        .fluidInputs(fluid('reduced_gold_free_pgm_solution') * 880)
        .fluidInputs(fluid('ammonium_chloride_solution') * 1140)
        .fluidOutputs(fluid('platinum_free_pgm_solution') * 310)
        .chancedOutput(metaitem('dustAmmoniumHexachloroplatinate') * 17, 6064, 0) // The chance is 57 / 94
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//DNHS SYNTHESIS
//C6H12 + H2S --UV-> C6H14S
//C6H12 + HBr --UV, H2O2-> C6H13BRr
//C6H14S + C6H13Br -> C12H26S + HBr

BR.recipeBuilder()
        .notConsumable(metaitem('carbon_arc_lamp'))
        .fluidInputs(fluid('one_hexene') * 50)
        .fluidInputs(fluid('hydrogen_sulfide') * 50)
        .fluidOutputs(fluid('one_hexanethiol') * 50)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

BR.recipeBuilder()
        .notConsumable(metaitem('carbon_arc_lamp'))
        .fluidInputs(fluid('one_hexene') * 50)
        .fluidInputs(fluid('hydrobromic_acid') * 50)
        .fluidOutputs(fluid('one_bromohexane') * 50)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

CSTR.recipeBuilder()
        .fluidInputs(fluid('one_hexanethiol') * 50)
        .fluidInputs(fluid('one_bromohexane') * 50)
        .fluidOutputs(fluid('hydrobromic_acid') * 50)
        .fluidOutputs(fluid('di_n_hexyl_sulfide') * 50)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()


//PALLADIUM

//Solvent extraction with DNHS
//Stripping with NH3(aq)
//Output: (Pd(NH3)4)Cl2 extract
//Output: Solution Ir,Rh

//Solution + di-n-hexyl-sulfide -> palladium extract + palladium-free pgm solution
//Extractant + (NH3)(H2O) -> (Pd(NH3)4)Cl2)
//(Pd(NH3)4)Cl2) + 2HCl -> (Pd(NH3)2)Cl2) + 2NH4Cl
//3[(Pd(NH3)2)Cl2)] -(inert atmosphere)-> 3Pd + 4NH4Cl + 2HCl + N2

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('platinum_free_pgm_solution') * 310)
        .fluidInputs(fluid('di_n_hexyl_sulfide') * 250)
        .fluidOutputs(fluid('palladium_extract') * 500)
        .fluidOutputs(fluid('rh_ir_solution') * 60)
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('palladium_extract') * 2000)
        .fluidInputs(fluid('ammonia_solution') * 1000)
        .fluidOutputs(fluid('di_n_hexyl_sulfide') * 1000)
        .fluidOutputs(fluid('tetraamminepalladium_dichloride') * 1000)
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// Pd(NH3)4Cl2(H2O) + 2HCl + H2O -> 2NH4Cl(H2O) + Pd(NH3)2Cl2
BR.recipeBuilder()
        .fluidInputs(fluid('tetraamminepalladium_dichloride') * 1000)
        .fluidInputs(fluid('hydrogen_chloride') * 2000)
        .fluidInputs(fluid('water') * 1000)
        .fluidOutputs(fluid('ammonium_chloride_solution') * 2000)
        .outputs(metaitem('dustDiamminedichloropalladium') * 11)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//IRIDIUM

//Oxidation with Cl2
//Solvent extraction with TBP
//Stripping with H2O
//Output: H2(IrCl6) extract
//Output: Solution Rh

// 2H3(IrCl6) + Cl2 + 2H2O + 2TBP -> 2H2(IrCl6)(TBP) + 2HCl(H2O) (Iridium solution)
// 

BCR.recipeBuilder()
        .fluidInputs(fluid('chlorine') * 5)
        .fluidInputs(fluid('rh_ir_solution') * 60)
        .fluidOutputs(fluid('chlorinated_rh_ir_solution') * 60)
        .duration(100)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MIXER.recipeBuilder()
        .fluidInputs(fluid('tributyl_phosphate') * 10)
        .fluidInputs(fluid('chlorinated_rh_ir_solution') * 60)
        .fluidOutputs(fluid('rh_ir_extraction_mixture') * 70)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

PHASE_SEPARATOR.recipeBuilder()
        .fluidInputs(fluid('rh_ir_extraction_mixture') * 70)
        .fluidOutputs(fluid('hexachlororhodic_acid_solution') * 50)
        .fluidOutputs(fluid('organic_iridium_solution') * 20)
        .duration(80)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// H2(IrCl6)(TBP) + HCl(H2O)(H2O) -> 
PHASE_SEPARATOR.recipeBuilder()
        .fluidInputs(fluid('organic_iridium_solution') * 200)
        .fluidInputs(fluid('dilute_hydrochloric_acid') * 200)
        .fluidOutputs(fluid('hexachloroirodic_acid_solution') * 100)
        .fluidOutputs(fluid('tributyl_phosphate') * 100)
        .duration(80)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

//H2(IrCl6)(TBP) + 2NH4Cl -> (NH3)2(IrCl6) + 2HCl

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('hexachloroiridic_acid_solution') * 1000)
        .fluidInputs(fluid('ammonium_chloride_solution') * 2000)
        .outputs(metaitem('dustAmmoniumHexachloroiridate') * 21)
        .fluidOutputs(fluid('hydrochloric_acid') * 2000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

REACTION_FURNACE.recipeBuilder()
        .inputs(ore('dustAmmoniumHexachloroiridate') * 21)
        .fluidInputs(fluid('hydrogen') * 4000)
        .outputs(metaitem('sponge.iridium'))
        .outputs(metaitem('dustAmmoniumChloride') * 24)
        .fluidOutputs(fluid('hydrogen_chloride') * 2000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(metaitem('sponge.iridium'))
        .outputs(metaitem('dustIridium'))
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()



//RHODIUM

//Crystallization
//Output: (NH4)3(RhCl6) crystals

CRYSTALLIZER.recipeBuilder()
        .fluidInputs(fluid('hexachlororhodic_acid_solution') * 1000)
        .inputs(ore('dustAmmoniumChloride') * 18)
        .outputs(metaitem('dustAmmoniumHexachlororhodate') * 21)
        .fluidOutputs(fluid('hydrochloric_acid') * 4000)
        .duration(200)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// H3IrCl6 + 3NH4Cl -> (NH4)3(IrCl6) + 3HCl + excess HCl solvent?
REACTION_FURNACE.recipeBuilder()
        .inputs(ore('dustAmmoniumHexachlororhodate') * 21)
        .fluidInputs(fluid('hydrogen') * 3000)
        .outputs(metaitem('sponge.rhodium'))
        .outputs(metaitem('dustAmmoniumChloride') * 18)
        .fluidOutputs(fluid('hydrogen_chloride') * 3000)
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

MACERATOR.recipeBuilder()
        .inputs(metaitem('sponge.rhodium'))
        .outputs(metaitem('dustRhodium'))
        .duration(240)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// PGM PROCESSING Tier 3

//Begin with HCl solution of Pt,Pd,Rh,Ir,Au,Ag,Ru

//Distillative removal of HCl
//Dilution with water
//Output: AgCl precipitate
//Output: Solution Pt,Pd,Rh,Ir,Au,Ru

//Solvent Extraction with MIBK
//Reduction
//Output: Au precipitate 
//Output: Solution Pt,Pd,Rh,Ir,Ru

//Solvent Extraction with oxime
//Stripping
//Output: H2(PdCl4) Solution
//Output: Solution Pt,Rh,Ir,Ru

//Solvent Extraction with amine
//Stripping
//Output: H2(PtCl6) Solution
//Output: Solution Ir,Rh,RU

//Removal of NH4+
//Oxidative Distillation
//Output: RuO4 distillate
//Output: Solution Ru,Ir

//Amine Extraction
//Stripping
//Output: H2(IrCl6) Solution
//Output: Solution Rh

//Ion Exchange
//Elution
//Output: H3(RhCl6) Solution
    
