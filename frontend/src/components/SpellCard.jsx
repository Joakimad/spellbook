import React from "react"
import "./SpellCard.css"

function SpellCard(props) {
  return (
    <div className="cardBody">

      <h1>{props.spell.name}</h1>
      <i>Level {props.spell.level} {props.spell.school}</i>

      <div className="edge" />

      <div className="cardSection">
        <b>Casting Time:</b>
        <p>{props.spell.castTime}</p>
      </div>

      <div className="cardSection">
        <b>Range:</b>
        <p>{props.spell.range}</p>
      </div>

      <div className="cardSection">
        <b>Duration:</b>
        <p>{props.spell.duration}</p>
      </div>

      <div className="edge" />

      <div className="cardDescription">
        <p>{props.spell.description}</p>
      </div>

      <div className="cardSection">
        <b>At Higher Levels:</b>
        <p>{props.spell.higherLevels}</p>
      </div>
    </div >
  )
}
export default SpellCard