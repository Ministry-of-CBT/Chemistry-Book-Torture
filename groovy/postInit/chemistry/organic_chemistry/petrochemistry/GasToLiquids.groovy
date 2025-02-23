import globals.Globals

REACTION_FURNACE = recipemap('reaction_furnace')
ELECTROMAGNETIC_SEPARATOR = recipemap('electromagnetic_separator')
PSA = recipemap('pressure_swing_adsorption')
PYROLYSE = recipemap('pyrolyse_oven')
BCR = recipemap('bubble_column_reactor')
FLBR = recipemap('fluidized_bed_reactor')
SIFTER = recipemap('sifter')
SIEVE_DT = recipemap('sieve_distillation')
CENTRIFUGE = recipemap('centrifuge')
HIGH_PRESSURE_DISTILLATION_TOWER = recipemap('high_pressure_cryogenic_distillation')

// Syngas production and refining

    // Light hydrocarbon reforming

    REACTION_FURNACE.recipeBuilder()
        .circuitMeta(1)
        .fluidInputs(fluid('hot_hp_methane') * 700)
        .fluidInputs(fluid('dense_steam') * 600)
        .fluidInputs(fluid('carbon_dioxide') * 400)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_hydrogen_rich_syngas') * 5000)
        .duration(10)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .circuitMeta(2)
        .fluidInputs(fluid('hot_hp_methane') * 900)
        .fluidInputs(fluid('dense_steam') * 600)
        .fluidInputs(fluid('carbon_dioxide') * 660)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_monoxide_rich_syngas') * 6360)
        .duration(10)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .circuitMeta(1)
        .fluidInputs(fluid('hot_hp_natural_gas') * 1400)
        .fluidInputs(fluid('dense_steam') * 1920)
        .fluidInputs(fluid('carbon_dioxide') * 880)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_hydrogen_rich_syngas') * 13610)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .circuitMeta(2)
        .fluidInputs(fluid('hot_hp_natural_gas') * 2310)
        .fluidInputs(fluid('dense_steam') * 3350)
        .fluidInputs(fluid('carbon_dioxide') * 2080)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_monoxide_rich_syngas') * 18020)
        .duration(30)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    REACTION_FURNACE.recipeBuilder()
        .fluidInputs(fluid('fuel_gas') * 2036)
        .fluidInputs(fluid('dense_steam') * 100)
        .fluidInputs(fluid('carbon_dioxide') * 1936)
        .notConsumable(ore('catalystBedSupportedNickel'))
        .fluidOutputs(fluid('raw_monoxide_rich_syngas') * 10180)
        .duration(20)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    // Purification

    SIFTER.recipeBuilder()
        .inputs(ore('dustMolecularSieve') * 36)
        .fluidInputs(fluid('raw_hydrogen_rich_syngas') * 12000)
        .fluidOutputs(fluid('hydrogen_rich_syngas') * 11280)
        .outputs(metaitem('dustCarbonatedMolecularSieve') * 36)
        .duration(4)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

    SIFTER.recipeBuilder()
        .inputs(ore('dustMolecularSieve') * 36)
        .fluidInputs(fluid('raw_monoxide_rich_syngas') * 12000)
        .fluidOutputs(fluid('monoxide_rich_syngas') * 11280)
        .outputs(metaitem('dustCarbonatedMolecularSieve') * 36)
        .duration(4)
        .EUt(Globals.voltAmps[2])
        .buildAndRegister()

// Syngas utilization

    // LT Fischer Tropsch

    BCR.recipeBuilder()
        .fluidInputs(fluid('monoxide_rich_syngas') * 11360)
        .notConsumable(ore('dustLtftCatalyst'))
        .fluidOutputs(fluid('raw_lt_syncrude') * 1000)
        .duration(20)
        .EUt(120)
        .buildAndRegister()

    CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('raw_lt_syncrude') * 2000)
        .fluidInputs(fluid('distilled_water') * 70)
        .fluidOutputs(fluid('lt_syncrude') * 1930)
        .fluidOutputs(fluid('oxygenate_solution') * 140)
        .duration(40)
        .EUt(30)
        .buildAndRegister()

    SIEVE_DT.recipeBuilder()
        .fluidInputs(fluid('lt_syncrude') * 1930)
        .fluidOutputs(fluid('synthetic_wax') * 80)
        .fluidOutputs(fluid('heavy_gas_oil') * 20)
        .fluidOutputs(fluid('light_gas_oil') * 80)
        .fluidOutputs(fluid('naphtha') * 260)
        .fluidOutputs(fluid('lt_light_gases') * 1490)
        .duration(40)
        .EUt(120)
        .buildAndRegister()

    HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('lt_light_gases') * 1490)
        .fluidOutputs(fluid('butane') * 40)
        .fluidOutputs(fluid('butene') * 50)
        .fluidOutputs(fluid('propane') * 70)
        .fluidOutputs(fluid('propene') * 50)
        .fluidOutputs(fluid('ethane') * 80)
        .fluidOutputs(fluid('ethylene') * 10)
        .fluidOutputs(fluid('methane') * 320)
        .fluidOutputs(fluid('hydrogen') * 870)
        .duration(40)
        .EUt(120)
        .buildAndRegister()

    // HT Fischer Tropsch

    FLBR.recipeBuilder()
        .fluidInputs(fluid('monoxide_rich_syngas') * 41700)
        .notConsumable(ore('dustHtftCatalyst'))
        .fluidOutputs(fluid('raw_ht_syncrude') * 5000)
        .duration(20)
        .EUt(120)
        .buildAndRegister()

    CENTRIFUGE.recipeBuilder()
        .fluidInputs(fluid('raw_ht_syncrude') * 5000)
        .fluidInputs(fluid('distilled_water') * 90)
        .fluidOutputs(fluid('ht_syncrude') * 4910)
        .fluidOutputs(fluid('oxygenate_solution') * 180)
        .duration(20)
        .EUt(30)
        .buildAndRegister()

    SIEVE_DT.recipeBuilder()
        .fluidInputs(fluid('ht_syncrude') * 4910)
        .fluidOutputs(fluid('synthetic_wax') * 10)
        .fluidOutputs(fluid('heavy_gas_oil') * 10)
        .fluidOutputs(fluid('light_gas_oil') * 45)
        .fluidOutputs(fluid('naphtha') * 365)
        .fluidOutputs(fluid('ht_light_gases') * 4480)
        .duration(20)
        .EUt(120)
        .buildAndRegister()

    HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('ht_light_gases') * 4480)
        .fluidOutputs(fluid('butane') * 170)
        .fluidOutputs(fluid('butene') * 180)
        .fluidOutputs(fluid('propane') * 270)
        .fluidOutputs(fluid('propene') * 280)
        .fluidOutputs(fluid('ethane') * 220)
        .fluidOutputs(fluid('ethylene') * 240)
        .fluidOutputs(fluid('methane') * 620)
        .fluidOutputs(fluid('hydrogen') * 2500)
        .duration(20)
        .EUt(120)
        .buildAndRegister()

    // Product treatment

    DT.recipeBuilder()
        .fluidInputs(fluid('oxygenate_solution') * 2000)
        .fluidOutputs(fluid('water') * 1000)
        .fluidOutputs(fluid('oxygenates') * 1000)
        .duration(10)
        .EUt(120)
        .buildAndRegister()

    // Hydrogen separation

        // WGSR

        REACTION_FURNACE.recipeBuilder()
            .fluidInputs(fluid('hydrogen_rich_syngas') * 6000)
            .fluidInputs(fluid('dense_steam') * 1000)
            .notConsumable(ore('catalystBedLtsCatalyst'))
            .fluidOutputs(fluid('reformed_hydrogen_rich_syngas') * 8000)
            .duration(100)
            .EUt(30)
            .buildAndRegister()

        REACTION_FURNACE.recipeBuilder()
            .fluidInputs(fluid('hydrogen_rich_syngas') * 6000)
            .fluidInputs(fluid('dense_steam') * 1000)
            .notConsumable(metaitem('catalystBedHtsCatalyst'))
            .fluidOutputs(fluid('reformed_hydrogen_rich_syngas') * 8000)
            .duration(30)
            .EUt(120)
            .buildAndRegister()

        REACTION_FURNACE.recipeBuilder()
            .fluidInputs(fluid('monoxide_rich_syngas') * 5000)
            .fluidInputs(fluid('dense_steam') * 1000)
            .notConsumable(ore('catalystBedLtsCatalyst'))
            .fluidOutputs(fluid('reformed_monoxide_rich_syngas') * 7000)
            .duration(100)
            .EUt(30)
            .buildAndRegister()

        REACTION_FURNACE.recipeBuilder()
            .fluidInputs(fluid('monoxide_rich_syngas') * 5000)
            .fluidInputs(fluid('dense_steam') * 1000)
            .notConsumable(metaitem('catalystBedHtsCatalyst'))
            .fluidOutputs(fluid('reformed_monoxide_rich_syngas') * 7000)
            .duration(30)
            .EUt(120)
            .buildAndRegister()

        // Separation

        PSA.recipeBuilder()
            .fluidInputs(fluid('reformed_hydrogen_rich_syngas') * 8000)
            .notConsumable(metaitem('dustMolecularSieve') * 5)
            .fluidOutputs(fluid('hydrogen') * 7000)
            .fluidOutputs(fluid('carbon_dioxide') * 1000)
            .duration(80)
            .EUt(120)
            .buildAndRegister()

        PSA.recipeBuilder()
            .fluidInputs(fluid('hydrogen_rich_syngas') * 6000)
            .notConsumable(metaitem('dustMolecularSieve') * 5)
            .fluidOutputs(fluid('hydrogen') * 5000)
            .fluidOutputs(fluid('carbon_monoxide') * 1000)
            .duration(80)
            .EUt(120)
            .buildAndRegister()

        PSA.recipeBuilder()
            .fluidInputs(fluid('reformed_monoxide_rich_syngas') * 7000)
            .notConsumable(metaitem('dustMolecularSieve') * 5)
            .fluidOutputs(fluid('hydrogen') * 6000)
            .fluidOutputs(fluid('carbon_dioxide') * 1000)
            .duration(80)
            .EUt(120)
            .buildAndRegister()

        PSA.recipeBuilder()
            .fluidInputs(fluid('monoxide_rich_syngas') * 5000)
            .notConsumable(metaitem('dustMolecularSieve') * 5)
            .fluidOutputs(fluid('hydrogen') * 4000)
            .fluidOutputs(fluid('carbon_monoxide') * 1000)
            .duration(80)
            .EUt(120)
            .buildAndRegister()

    // Methanol to gasoline

    FLBR.recipeBuilder()
        .fluidInputs(fluid('methanol') * 4460)
        .notConsumable(ore('catalystBedZsmFive'))
        .fluidOutputs(fluid('methanol_dehydration_overheads') * 580)
        .fluidOutputs(fluid('naphtha') * 420)
        .fluidOutputs(fluid('water') * 4460)
        .duration(20)
        .EUt(120)
        .buildAndRegister()

    HIGH_PRESSURE_DISTILLATION_TOWER.recipeBuilder()
        .fluidInputs(fluid('methanol_dehydration_overheads') * 580)
        .fluidOutputs(fluid('butane') * 20)
        .fluidOutputs(fluid('isobutane') * 160)
        .fluidOutputs(fluid('butene') * 80)
        .fluidOutputs(fluid('propane') * 80)
        .fluidOutputs(fluid('propene') * 75)
        .fluidOutputs(fluid('ethane') * 65)
        .fluidOutputs(fluid('methane') * 100)
        .duration(20)
        .EUt(120)
        .buildAndRegister()