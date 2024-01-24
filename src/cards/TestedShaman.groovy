package cards
class AlAkirTheWindlord extends CardDefinition {
	AlAkirTheWindlord() {
		name="Al'Akir the Windlord"; type='minion'; cost=8; attack=3; max_health=5
		text='Windfury, Charge, Divine Shield, Taunt'
		reserved_to="Shaman"
		when_coming_in_play(text) {
			this_minion.gain( WINDFURY )
			this_minion.gain( CHARGE )
			this_minion.gain( DIVINE_SHIELD )
			this_minion.gain( TAUNT )
		}
	}
}

class EarthElemental extends CardDefinition {