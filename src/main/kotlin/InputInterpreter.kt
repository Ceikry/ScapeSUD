import Entity.Player
import actions.Action

object InputInterpreter {
    fun handle(line: String, player: Player){
        val tokens = line.split(" ")
        var action: Action?
        if(GameConstants.BUILD_MODE){
            action = BuildRepository.search(tokens[0])
        } else {
            action = InputRepository.search(tokens[0])
        }
        if(action == null && GameConstants.BUILD_MODE){
            action = InputRepository.search(tokens[0])
        }
        if(action == null){
            GameConstants.textQueue += System.lineSeparator() + "What?"
            GameConstants.gui.refresh()
            return
        }
        action.handle(player, tokens.toTypedArray())
        GameConstants.gui.refresh()
    }
}