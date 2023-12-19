package day04

class Game(private val gameCards: List<GameCard>) {

    fun playGame(): Int {
        val allCards = gameCards.toMutableList()
        for (card in gameCards) {
            allCards.addAll(play(card))
        }
        return allCards.size

    }

    private fun play(gameCard: GameCard): List<GameCard> {
        val extraGameCards = mutableListOf<GameCard>()

        val extra = gameCard.getActualWinningsSet().size
        val ids = gameCard.id + 1..(gameCard.id + extra)
        val additionalCards = ids.mapNotNull { id -> gameCards.find { id == it.id } }
        if (additionalCards.isEmpty()) {
            return emptyList()
        }
        extraGameCards.addAll(additionalCards)
        for (additionalCard in additionalCards) {
            extraGameCards.addAll(play(additionalCard))
        }

        return extraGameCards
    }
}