package com.example.planty.ui.createEntry

data class CreatePlantyUiState(
    val plantName: String = "",
    val waterReq: SliderValues = SliderValues.MODERATE,
    val lightReq: SliderValues = SliderValues.MODERATE,
    val adoptionDate: String = "",
    val location: String = "",
    val plantType: String = "",
) {
    val sliderValues: List<SliderValues>
        get() = listOf(*SliderValues.values())
    val adoptionMenuOptions: List<String>
        get() = listOf("Today", "Yesterday", "Tomorrow")
    val locationMenuOptions: List<String>
        get() = listOf("Living Room", "Bedroom", "Kitchen", "Bathroom")
    val plantTypeMenuOptions: List<String>
        get() = listOf("Cactus", "Flower", "Tree")

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
