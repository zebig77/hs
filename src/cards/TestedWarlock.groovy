package cards

class BaneOfDoom extends CardDefinition {
	BaneOfDoom() {
		name='Bane of Doom'; type='spell'; cost=5
		text='Deal 2 damage to a character. If that kills it, summon a random Demon.'
		reserved_to="Warlock"
		when_played(text) {
			def t = select_spell_target( all_characters - your_hero )
			this_spell.deal_spell_damage(2,t)
			if (t.is_dead()) {
				Game.summon( you,
						random_pick(
						[
							'Dread Infernal',
							'Succubus',
							'Voidwalker',
							'Blood Imp',
							'Felguard' ] ) )
			}
		}
	}
}