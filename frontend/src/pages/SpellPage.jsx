import React, { useState, useEffect } from "react"
import SpellCard from "../components/SpellCard"
import "./SpellPage.css"

export default function SpellPage({ match }) {

    useEffect(() => {
        fetchSpell();
    }, [])

    const [spell, setSpell] = useState({})

    const fetchSpell = async () => {
        const data = await fetch(`http://localhost:5000/spell/name/${match.params.name}`);
        const spell = await data.json();
        setSpell(spell)
    }

    return (
        <div className="SpellPage">
            <SpellCard
                spell={{
                    name: spell.name,
                    level: spell.level,
                    school: spell.school,
                    castTime: spell.castTime,
                    range: spell.spellRange,
                    duration: spell.duration,
                    description: spell.description,
                    higherLevels: spell.higherLevels
                }} />
        </div>
    )
}