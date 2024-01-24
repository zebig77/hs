package cards
	AncientOfLore() {
		name='Ancient of Lore'; type='minion'; cost=7; attack=5; max_health=5
		text='Choose One - Draw 2 cards; or Restore 5 Health.'
		reserved_to="Druid"
		when_played(text) {
			def script1 = {	you.draw(2) }
			def script2 = {	this_minion.restore_health(5, select_spell_target(all_targets)) }
			you.choose(druid_choices, [script1, script2])
		}
	}
}