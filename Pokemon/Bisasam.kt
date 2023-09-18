package Pokemon

class Bisasam(
    name: String,
    lvl: Int,
    lp: Int,
    angriff: Int,
    verteildigung: Int,
    attack: MutableList<String>,
    typ: String
) : Pokemon(
    "Bisasam", 5, 45, 49, 49, mutableListOf(
        "Tackle",
        "Rankenhieb"
    ), "Pflanzen"
) {


}
