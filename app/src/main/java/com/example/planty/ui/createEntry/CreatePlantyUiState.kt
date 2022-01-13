package com.example.planty.ui.createEntry

data class CreatePlantyUiState(
    val plantName: String = "",
    val waterReq: SliderValues = SliderValues.MODERATE,
    val lightReq: SliderValues = SliderValues.MODERATE,
    val adoptionDate: AdoptionMenuOptions = BlankAdoption,
    val location: LocationMenuOptions = BlankLocation,
    val plantType: PlantTypeMenuOptions = BlankPlantType,
) {
    val sliderValues: List<SliderValues>
        get() = listOf(*SliderValues.values())
    val adoptionMenuOptions: List<AdoptionMenuOptions>
        get() = listOf(Today, Yesterday, Tomorrow)
    val locationMenuOptions: List<LocationMenuOptions>
        get() = listOf(LivingRoom, Bedroom, Kitchen, Bathroom)
    val plantTypeMenuOptions: List<PlantTypeMenuOptions>
        get() = listOf(Cactus, Flower, Tree)

    val startingSliderPosition: Float
        get() = (sliderValues.size / 2).toFloat()
}

enum class SliderValues(val value: String) {
    LITTLE("Little"),
    SOME("Some"),
    MODERATE("Moderate"),
    LOTS("Lots"),
    TONS("Tons")
}

sealed class DropdownMenuOptions(val value: String)

sealed class AdoptionMenuOptions(value: String) : DropdownMenuOptions(value)
object BlankAdoption : AdoptionMenuOptions("")
object Today : AdoptionMenuOptions("Today")
object Yesterday : AdoptionMenuOptions("Yesterday")
object Tomorrow : AdoptionMenuOptions("Tomorrow")

sealed class LocationMenuOptions(value: String) : DropdownMenuOptions(value)
object BlankLocation : LocationMenuOptions("")
object LivingRoom : LocationMenuOptions("Living Room")
object Bedroom : LocationMenuOptions("Bedroom")
object Kitchen : LocationMenuOptions("Kitchen")
object Bathroom : LocationMenuOptions("Bathroom")

sealed class PlantTypeMenuOptions(value: String) : DropdownMenuOptions(value)
object BlankPlantType : PlantTypeMenuOptions("")
object Cactus : PlantTypeMenuOptions("Cactus")
object Flower : PlantTypeMenuOptions("Flower")
object Tree : PlantTypeMenuOptions("Tree")
