import static globals.Globals.*

SIFTER = recipemap('sifter')
MIXER = recipemap('mixer')
VACUUM_CHAMBER = recipemap('vacuum_chamber')
FLUID_COMPRESSOR = recipemap('fluid_compressor')
FLUID_DECOMPRESSOR = recipemap('fluid_decompressor')
HEAT_EXCHANGER = recipemap('heat_exchanger')
PHASE_SEPARATOR = recipemap('phase_separator')
LOW_PRESSURE_DISTILLATION_TOWER = recipemap('low_pressure_cryogenic_distillation')
HIGH_PRESSURE_DISTILLATION_TOWER = recipemap('high_puressure_cryogenic_distillation')
SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT = recipemap('single_column_cryogenic_distillation')
VENT = recipemap('smoke_stack')
BATH_CONDENSER = recipemap('bath_condenser')
SMOKE_STACK = recipemap('smoke_stack')
FBR = recipemap('fixed_bed_reactor')
SINTERING_OVEN = recipemap('sintering_oven')
CENTRIFUGE = recipemap('centrifuge')
FLUID_HEATER = recipemap('fluid_heater')

//TIER ONE (OXYGEN + NITROGEN)

/*AIR PREPROCESSING
//COMPRESSION
FLUID_COMPRESSOR.recipeBuilder()
    .fluidInputs(fluid('air') * 6000)
    .fluidOutputs(fluid('compressed_air') * 1000)
    .duration(31)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

//WATER COOLING
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('liquid_nitrogen') * 10)
    .fluidInputs(fluid('water') * 1280)
    .fluidOutputs(fluid('nitrogen') * 640)
    .fluidOutputs(fluid('cold_water') * 1280)
    .duration(31)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('purified_waste_gaseous_nitrogen') * 640)
    .fluidInputs(fluid('water') * 1280)
    .fluidOutputs(fluid('nitrogen') * 640)
    .fluidOutputs(fluid('cold_water') * 1280)
    .duration(5)
    .buildAndRegister()

//AIR WASHING AND PRECOOLING
CONTACT_COOLER.recipeBuilder()
    .fluidInputs(fluid('compressed_air') * 1000)
    .fluidInputs(fluid('cold_water') * 640)
    .fluidOutputs(fluid('chilled_air') * 1000)
    .fluidOutputs(fluid('water') * 640)
    .duration(31)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()*/

//CO2 REMOVAL
SIFTER.recipeBuilder()
    .inputs(ore('dustTinyMolecularSieve'))
    .fluidInputs(fluid('air') * 12000)
    .fluidOutputs(fluid('decarburized_air') * 12000)
    .outputs(metaitem('dustTinyDirtyMolecularSieve'))
    .duration(4)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//VACUUM REHEATING FOR REGENERATION
VACUUM_CHAMBER.recipeBuilder()
    .inputs(ore('dustTinyDirtyMolecularSieve'))
    .notConsumable(metaitem('springKanthal'))
    .outputs(metaitem('dustTinyMolecularSieve'))
    .fluidOutputs(fluid('carbon_dioxide') * 20)
    .duration(4)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('chilly_waste_gaseous_nitrogen') * 640)
    .fluidInputs(fluid('hp_decarburized_air') * 1000)
    .fluidOutputs(fluid('waste_gaseous_nitrogen') * 640)
    .fluidOutputs(fluid('cold_hp_decarburized_air') * 1000)
    .duration(1)
    .buildAndRegister()

SMOKE_STACK.recipeBuilder()
    .fluidInputs(fluid('waste_gaseous_nitrogen') * 640)
    .duration(1)
    .buildAndRegister()

//SIDESTREAM OF BOOSTED AIR FOR COLD GENERATION
/*FLUID_COMPRESSOR.recipeBuilder()
    .fluidInputs(fluid('purified_air') * 1000)
    .fluidOutputs(fluid('boosted_air') * 300)
    .duration(114)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

//FIVE LANE HEAT EXCHANGER
TAPPED_OFF_HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('boosted_air') * 300)
    .fluidInputs(fluid('purified_air') * 500)
    .fluidOutputs(fluid('chilled_boosted_air') * 150)
    .fluidOutputs(fluid('partially_liquefied_air') * 150)
    .fluidOutputs(fluid('gaseous_air_feedstock') * 500)
    .duration(5)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('purified_air') * 500)
    .fluidInputs(fluid('untreated_liquid_oxygen') * 15)
    .fluidOutputs(fluid('gaseous_air_feedstock') * 500)
    .fluidOutputs(fluid('oxygen') * 720)
    .duration(5)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('untreated_liquid_oxygen') * 10)
    .fluidInputs(fluid('chilly_waste_gaseous_nitrogen') * 320)
    .fluidOutputs(fluid('oxygen') * 480)
    .fluidOutputs(fluid('waste_gaseous_nitrogen') * 320)
    .duration(5)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('chilly_waste_gaseous_nitrogen') * 320)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 25)
    .fluidOutputs(fluid('waste_gaseous_nitrogen') * 320)
    .fluidOutputs(fluid('nitrogen') * 1200)
    .duration(5)
    .buildAndRegister()

//STARTUP HEAT EXCHANGE
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('purified_air') * 500)
    .fluidInputs(fluid('liquid_oxygen') * 10)
    .fluidOutputs(fluid('gaseous_air_feedstock') * 500)
    .fluidOutputs(fluid('oxygen') * 640)
    .duration(5)
    .buildAndRegister()

//FINAL PREPROCESSING
FLUID_DECOMPRESSOR.recipeBuilder()
    .fluidInputs(fluid('chilled_boosted_air') * 150)
    .fluidOutputs(fluid('gaseous_air_feedstock') * 500)
    .duration(114)
    .EUt(Globals.voltAmps[1])
    .buildAndRegister()

PHASE_SEPARATOR.recipeBuilder()
    .fluidInputs(fluid('partially_liquified_air') * 150)
    .fluidOutputs(fluid('liquid_air_feedstock') * 50)
    .fluidOutputs(fluid('gaseous_air_feedstock') * 100)
    .duration(114)
    .buildAndRegister()*/

//STARTUP DISTILLATION RECIPE
HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('liquid_decarburized_air') * 100)
    .fluidInputs(fluid('cold_hp_decarburized_air') * 5600)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 50)
    .fluidOutputs(fluid('oxygen_rich_gas') * 200)
    .fluidOutputs(fluid('nitrogen_rich_gas') * 1400)
    .duration(20)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//REFLUXED DISTILLATION RECIPE
BATH_CONDENSER.recipeBuilder()
    .fluidInputs(fluid('nitrogen_rich_gas') * 200)
    .fluidOutputs(fluid('untreated_liquid_nitrogen') * 25)
    //.cleanroom(CleanroomType.LOW_PRESSURE_DISTILLATION_TOWER)
    .duration(1)
    .buildAndRegister()

HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(2)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 25)
    .fluidInputs(fluid('liquid_decarburized_air') * 100)
    .fluidInputs(fluid('cold_hp_decarburized_air') * 5600)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 50)
    .fluidOutputs(fluid('oxygen_rich_gas') * 200)
    .fluidOutputs(fluid('nitrogen_rich_gas') * 1600)
    .duration(5)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//UPPER COLUMN FOR FINAL AIR SEPARATION
//STARTUP
LOW_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('oxygen_rich_liquid') * 50)
    .fluidInputs(fluid('oxygen_rich_gas') * 800)
    .fluidInputs(fluid('nitrogen_rich_gas') * 3200)
    .fluidOutputs(fluid('untreated_liquid_oxygen') * 50)
    .fluidOutputs(fluid('cold_waste_gaseous_nitrogen') * 5440)
    .fluidOutputs(fluid('argon_rich_gas') * 200)
    .duration(20)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//SUBCOOLED
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('cold_waste_gaseous_nitrogen') * 640)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 50)
    .fluidOutputs(fluid('chilly_waste_gaseous_nitrogen') * 640)
    .fluidOutputs(fluid('subcooled_liquid_nitrogen') * 50)
    .duration(5)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 25)
    .fluidInputs(fluid('untreated_liquid_oxygen') * 50)
    .fluidOutputs(fluid('subcooled_liquid_nitrogen') * 25)
    .fluidOutputs(fluid('liquid_oxygen_product') * 50)
    .duration(5)
    .buildAndRegister()
    
LOW_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(2)
    .fluidInputs(fluid('oxygen_rich_liquid') * 50)
    .fluidInputs(fluid('oxygen_rich_gas') * 800)
    .fluidInputs(fluid('nitrogen_rich_gas') * 3200)
    .fluidInputs(fluid('subcooled_liquid_nitrogen') * 50)
    .fluidOutputs(fluid('untreated_liquid_oxygen') * 50)
    .fluidOutputs(fluid('cold_waste_gaseous_nitrogen') * 640)
    .fluidOutputs(fluid('liquid_nitrogen') * 150)
    .fluidOutputs(fluid('argon_rich_gas') * 200)
    .duration(5)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

SMOKE_STACK.recipeBuilder()
    .fluidInputs(fluid('argon_rich_gas') * 200)
    .duration(5)
    .buildAndRegister()

CENTRIFUGE.recipeBuilder()
    .fluidInputs(fluid('liquid_nitrogen_product') * 100)
    .fluidOutputs(fluid('liquid_nitrogen') * 75)
    .duration(1)
    .EUt(Globals.voltAmps[0])
    .buildAndRegister()

CENTRIFUGE.recipeBuilder()
    .fluidInputs(fluid('liquid_oxygen_product') * 100)
    .fluidOutputs(fluid('liquid_oxygen') * 75)
    .duration(1)
    .EUt(Globals.voltAmps[0])
    .buildAndRegister()

//TIER TWO: ADDED ARGON

//NO GAS TAPOFF
HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(3)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 25)
    .fluidInputs(fluid('liquid_decarburized_air') * 100)
    .fluidInputs(fluid('cold_hp_decarburized_air') * 5600)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 75)
    .fluidOutputs(fluid('nitrogen_rich_gas') * 1600)
    .duration(5)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//STARTUP DISTILLATION
SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('argon_rich_gas') * 800)
    .fluidOutputs(fluid('crude_argon_vapor') * 80)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 90)
    .duration(20)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//REFLUXED DISTILLATION
SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT.recipeBuilder()
    .circuitMeta(2)
    .fluidInputs(fluid('liquid_crude_argon') * 5)
    .fluidInputs(fluid('argon_rich_gas') * 400)
    .fluidOutputs(fluid('crude_argon_vapor') * 120)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 90)
    .duration(10)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//CONDENSATION
BATH_CONDENSER.recipeBuilder()
    .fluidInputs(fluid('subcooled_oxygen_rich_liquid') * 150)
    .fluidInputs(fluid('crude_argon_vapor') * 60)
    .fluidOutputs(fluid('liquid_crude_argon') * 15)
    .fluidOutputs(fluid('oxygen_rich_liquid') * 100)
    .fluidOutputs(fluid('oxygen_rich_gas') * 400)
    //.cleanroom(CleanroomType.SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT)
    .duration(1)
    .buildAndRegister()

//DEOXYGENATION
MIXER.recipeBuilder()
    .inputs(ore('dustAmmoniumHexachloroplatinate') * 17)
    .fluidInputs(fluid('phosphoric_acid') * 1000)
    .fluidOutputs(fluid('deoxygenation_catalyst_precursor_solution') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

SINTERING_OVEN.recipeBuilder()
    .inputs(ore('dustAlumina') * 5)
    .fluidInputs(fluid('deoxygenation_catalyst_precursor_solution') * 1000)
    .outputs(metaitem('dustDeoxygenationCatalyst'))
    .fluidOutputs(fluid('phosphoric_acid') * 1000)
    .duration(100)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

FBR.recipeBuilder()
    .fluidInputs(fluid('liquid_crude_argon') * 50)
    .fluidInputs(fluid('hydrogen') * 24)
    .notConsumable(metaitem('catalystBedDeoxygenationCatalyst'))
    .chancedOutput(metaitem('dustIce'), 120, 0)
    .fluidOutputs(fluid('liquid_deoxygenated_argon') * 50)
    .duration(20)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//ARGON PURIFICATION
//STARTUP DISTILLATION
SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT.recipeBuilder()
    .fluidInputs(fluid('liquid_deoxygenated_argon') * 200)
    .fluidOutputs(fluid('cold_waste_gaseous_nitrogen') * 16)
    .fluidOutputs(fluid('liquid_argon_product') * 200)
    .duration(200)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//REFLUXED DISTILLATION
BATH_CONDENSER.recipeBuilder()
    .fluidInputs(fluid('cold_waste_gaseous_nitrogen') * 8)
    .fluidOutputs(fluid('liquid_waste_nitrogen') * 1)
    //.cleanroom(CleanroomType.SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT)
    .duration(1)
    .buildAndRegister()

SINGLE_COLUMN_CRYOGENIC_DISTILLATION_PLANT.recipeBuilder()
    .fluidInputs(fluid('liquid_deoxygenated_argon') * 200)
    .fluidInputs(fluid('argon') * 400)
    .fluidInputs(fluid('liquid_waste_nitrogen'))
    .fluidOutputs(fluid('cold_waste_gaseous_nitrogen') * 24)
    .fluidOutputs(fluid('liquid_argon_product') * 200)
    .duration(83)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//STARTUP SUBCOOLING
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('oxygen_rich_liquid') * 75)
    .fluidInputs(fluid('liquid_nitrogen') * 150)
    .fluidOutputs(fluid('subcooled_oxygen_rich_liquid') * 75)
    .fluidOutputs(fluid('nitrogen') * 9600)
    .duration(5)
    .buildAndRegister()

//NORMAL SUBCOOLING
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('oxygen_rich_liquid') * 75)
    .fluidInputs(fluid('liquid_argon_product') * 5)
    .fluidOutputs(fluid('subcooled_oxygen_rich_liquid') * 75)
    .fluidOutputs(fluid('partially_liquefied_argon') * 18)
    .duration(5)
    .buildAndRegister()

PHASE_SEPARATOR.recipeBuilder()
    .fluidInputs(fluid('partially_liquefied_argon') * 450)
    .fluidOutputs(fluid('argon') * 400)
    .fluidOutputs(fluid('liquid_argon_product') * 50)
    .duration(29)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//ADAPTED LOW PRESSURE RECIPE
LOW_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
    .circuitMeta(3)
    .fluidInputs(fluid('oxygen_rich_liquid') * 100)
    .fluidInputs(fluid('oxygen_rich_gas') * 1600)
    .fluidInputs(fluid('nitrogen_rich_gas') * 6400)
    .fluidInputs(fluid('subcooled_liquid_nitrogen') * 100)
    .fluidOutputs(fluid('untreated_liquid_oxygen') * 105)
    .fluidOutputs(fluid('cold_waste_gaseous_nitrogen') * 1280)
    .fluidOutputs(fluid('liquid_nitrogen_product') * 300)
    .fluidOutputs(fluid('argon_rich_gas') * 400)
    .duration(5)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

//ADAPTED HEAT EXCHANGE (SUBCOOLING)
HEAT_EXCHANGER.recipeBuilder()
    .fluidInputs(fluid('cold_waste_gaseous_nitrogen') * 1280)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 100)
    .fluidOutputs(fluid('chilly_waste_gaseous_nitrogen') * 1280)
    .fluidOutputs(fluid('subcooled_liquid_nitrogen') * 100)
    .duration(5)
    .buildAndRegister()

HEAT_EXCHANGER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('untreated_liquid_nitrogen') * 100)
    .fluidInputs(fluid('untreated_liquid_oxygen') * 55)
    .fluidOutputs(fluid('subcooled_liquid_nitrogen') * 100)
    .fluidOutputs(fluid('liquid_oxygen_product') * 55)
    .duration(5)
    .buildAndRegister()

//BOILING
FLUID_HEATER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('liquid_nitrogen') * 100)
    .fluidOutputs(fluid('nitrogen') * 6400)
    .duration(2)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

FLUID_HEATER.recipeBuilder()
    .circuitMeta(1)
    .fluidInputs(fluid('liquid_oxygen') * 100)
    .fluidOutputs(fluid('oxygen') * 6400)
    .duration(2)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()

FLUID_HEATER.recipeBuilder()
    .fluidInputs(fluid('liquid_argon') * 100)
    .fluidOutputs(fluid('argon') * 6400)
    .duration(2)
    .EUt(Globals.voltAmps[3])
    .buildAndRegister()