import React from "react";
import styled from "styled-components";
import Button from "react-bootstrap/lib/Button";
import FormGroup from "react-bootstrap/lib/FormGroup";
import Form from "react-bootstrap/lib/Form";
import FormControl from "react-bootstrap/lib/FormControl";
import ControlLabel from "react-bootstrap/lib/ControlLabel";
import Panel from "react-bootstrap/lib/Panel";

import MangaService from "../service/MangaService.jsx";

const PanelForm = styled.div`
  width: 400px;
  margin-left: auto;
  margin-right: auto;
`;
export default class Manga extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      manga: undefined,
      url: "",
      showModal: false
    };
    this.handleChange = this.handleChange.bind(this);
    this.scrap = this.scrap.bind(this);
  }

  handleChange(e) {
    this.setState({ url: e.target.value });
  }
  scrap() {
    MangaService.scrapManga(this.state.url).then(mangaGet => {
      this.setState({
        manga: {
          author: mangaGet.data.author,
          chapters: mangaGet.data.chapters,
          name: mangaGet.data.name,
          image: mangaGet.data.image
        }
      });
    });
  }

  render() {
    return (
      <PanelForm>
        <Panel>
          <Form>
            <FormGroup controlId="formBasicEmail">
              <ControlLabel>Url du manga address</ControlLabel>
              <FormControl
                type="url"
                placeholder="Enter your url"
                onChange={this.handleChange}
                value={this.state.url}
              />
              <FormControl.Feedback />
            </FormGroup>
            <Button onClick={() => this.scrap()} type="button">
              Scrap
            </Button>
          </Form>
        </Panel>
      </PanelForm>
    );
  }
}
