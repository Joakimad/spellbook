import SpellTable from "./pages/SpellTable"
import SpellPage from "./pages/SpellPage"
import { BrowserRouter as Router, Switch, Route } from "react-router-dom"
import './App.css'

function App() {
    return (
        <Router>
            <div className="App">
                <Switch>
                    <Route path="/" exact component={SpellTable} />
                    <Route path="/spell/:name" component={SpellPage} />
                </Switch>
            </div>
        </Router>
    )
}

export default App;