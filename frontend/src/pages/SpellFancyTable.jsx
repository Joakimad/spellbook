import React from "react";
import Table from '@material-ui/core/Table';
import TableBody from '@material-ui/core/TableBody';
import TableCell from '@material-ui/core/TableCell';
import TableContainer from '@material-ui/core/TableContainer';
import TableHead from '@material-ui/core/TableHead';
import TableRow from '@material-ui/core/TableRow';
import Paper from '@material-ui/core/Paper';
import CheckIcon from '@material-ui/icons/Check';

class SpellFancyTable extends React.Component {

  constructor() {
    super()
    this.state = {
      spells: []
    }
  }

  componentDidMount() {
    console.log("Starting fetch...")
    fetch("http://localhost:5000/spell")
      .then(response => response.json())
      .then(data => {
        this.setState({
          spells: data
        })
      })
  }

  render() {
    return (
      <TableContainer component={Paper}>
        <Table aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell align="right">Level</TableCell>
              <TableCell align="right">School</TableCell>
              <TableCell align="right">Cast Time</TableCell>
              <TableCell align="right">Range</TableCell>
              <TableCell align="right">Duration</TableCell>
              <TableCell align="right">Concentration</TableCell>
              <TableCell align="right">Ritual</TableCell>
              <TableCell align="right">Source</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {this.state.spells.map((spell) => (
              <TableRow key={spell.name}>
                <TableCell component="th" scope="row" >
                  {spell.name}
                </TableCell>
                <TableCell align="right">{spell.level}</TableCell>
                <TableCell align="right">{spell.school}</TableCell>
                <TableCell align="right">{spell.castTime}</TableCell>
                <TableCell align="right">{spell.spellRange}</TableCell>
                <TableCell align="right">{spell.duration}</TableCell>
                <TableCell align="right">{this.displayCheckmark(spell.concentration)}</TableCell>
                <TableCell align="right">{this.displayCheckmark(spell.ritual)}</TableCell>
                <TableCell align="right">{spell.source}</TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    );
  }

  displayCheckmark(bool){
    if (bool === true) {
      return <CheckIcon/>
    }
  }

  handleOnClick() {
    console.log("CLICK")
  }

}

export default SpellFancyTable;


/**
 *
  const api = axios.create({
    baseURL: 'http://localhost:5000/spell/'
  })


  const columns = React.useMemo(
    () => [
      {
        Header: 'Column 1',
        accessor: 'col1', // accessor is the "key" in the data
      },
      {
        Header: 'Column 2',
        accessor: 'col2',
      },
    ],
    []
  )

  const data = React.useMemo(
    () => [
      {
        col1: 'Hello',
        col2: 'World',
      },
      {
        col1: 'react-table',
        col2: 'rocks',
      },
      {
        col1: 'whatever',
        col2: 'you want',
      },
    ],
    []
  )
 */