BR = recipemap('batch_reactor')

BR.recipeBuilder()
    .fluidInputs(fluid('antimony_dross') * 720)
    .fluidInputs(fluid('chlorine') * 6000)
    .outputs(metaitem('dustAntimony') * 2)
    .outputs(metaitem('dustMagnesiumChloride') * 6)
    .outputs(metaitem('dustCalciumChloride') * 3)
    .EUt(120)
    .duration(200)
    .buildAndRegister()