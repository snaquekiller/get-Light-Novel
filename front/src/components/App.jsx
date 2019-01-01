import React from "react";
import Button from "react-bootstrap/lib/Button";
import FormGroup from "react-bootstrap/lib/FormGroup";
import Form from "react-bootstrap/lib/Form";
import FormControl from "react-bootstrap/lib/FormControl";
import ControlLabel from "react-bootstrap/lib/ControlLabel";

import MangaService from "../service/MangaService.jsx";

export default class App extends React.Component {
  scrap() {
    MangaService.scrapManga("coucou");
    console.log("coucou");
  }

  render() {
    return (
      <div style={{ textAlign: "center" }}>
        <h1>Put your url here</h1>
        <Form>
          <FormGroup controlId="formBasicEmail">
            <ControlLabel>Email address</ControlLabel>
            <FormControl type="url" placeholder="Enter your url" />
            L'ets scrap this manga :)
          </FormGroup>
          <Button onClick={() => this.scrap()} type="button">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
}