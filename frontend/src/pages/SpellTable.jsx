import React, { useState, useEffect } from "react"
import { Link } from 'react-router-dom'
import './SpellTable.css'

function SpellTable() {

    useEffect(() => {
        fetchSpells();
    }, [])

    const [spells, setSpells] = useState([])

    const fetchSpells = async () => {
        const data = await fetch('http://localhost:5000/spell');
        const items = await data.json();
        setSpells(items)
    }

    function formatPath(path) {
        return path.replace(/\s+/g, "-").toLowerCase();
    }

    return (
        <div className="SpellTable">
            <table>
                <tr>
                    <th>Name</th>
                    <th>Level</th>
                    <th>School</th>
                    <th>Casting Time</th>
                    <th>Duration</th>
                    <th>Range</th>
                </tr>
                {spells.map(spell => (
                    <tr>
                        <td>
                            <h1 key={spell.name}>
                                <Link to={
                                    `/spell/${formatPath(spell.name)}`}>{spell.name}</Link>
                            </h1>
                        </td>
                        <td>{spell.level}</td>
                        <td>{spell.school}</td>
                        <td>{spell.castTime}</td>
                        <td>{spell.duration}</td>
                        <td>{spell.spellRange}</td>
                    </tr>
                ))}
            </table>
        </div>
    )
}
export default SpellTable