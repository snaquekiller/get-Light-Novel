import React from "react";
import Button from "react-bootstrap/lib/Button";
import FormGroup from "react-bootstrap/lib/FormGroup";
import Form from "react-bootstrap/lib/Form";
import FormControl from "react-bootstrap/lib/FormControl";
import ListGroupItem from "react-bootstrap/lib/ListGroupItem";
import ListGroup from "react-bootstrap/lib/ListGroup";
import Image from "react-bootstrap/lib/Image";
import ControlLabel from "react-bootstrap/lib/ControlLabel";
import Panel from "react-bootstrap/lib/Panel";
import styled from "styled-components";
import { Modal } from "react-overlays"

import MangaService from "../service/MangaService.jsx";

const Manga = styled.div`
  width: 400px;
  img {
    max-width: 200px;
    margin-left: auto;
    margin-right: auto;
  }

  .mangaName {
    font-weight: bold;
    font-size: 20px;
    mcolor: #050c84;
  }
  .title {
    font-weight: bold;
    mcolor: #050c84;
  }

  .text {
    font-weight: normal;
    color: #333;
  }
  .list-group {
    max-width: 150px;
    margin-left: auto;
    margin-right: auto;
    img {
      padding: 5px 15px;
    }
  }
`;

const backdropStyle = {
  position: 'fixed',
  zIndex: 1040,
  top: 0,
  bottom: 0,
  left: 0,
  right: 0,
  backgroundColor: '#000',
  opacity: 0.5
};

const modalStyle = function() {
  // we use some psuedo random coords so nested modals
  // don't sit right on top of each other.
  let top = 50;
  let left = 50;

  return {
    position: 'fixed',
    width: 400,
    zIndex: 1040,
    top: top + '%',
    left: left + '%',
    border: '1px solid #e5e5e5',
    backgroundColor: 'white',
    boxShadow: '0 5px 15px rgba(0,0,0,.5)',
    padding: 20
  };
};

const PanelForm = styled.div`
  width: 400px;
  margin-left: auto;
  margin-right: auto;
`;
export default class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      manga: undefined,
      url: "",
      showModal: false
    };
    this.scrap = this.scrap.bind(this);
    this.handleChange = this.handleChange.bind(this);

    this.close = () => {
      this.setState({ showModal: false });
    };

    this.open = () => {
      this.setState({ showModal: true });
    };
  }

  handleChange(e) {
    this.setState({ url: e.target.value });
  }

  renderBackdrop(props) {
    return <div {...props} style={backdropStyle} />;
  }

  scrap() {
    MangaService.scrapManga(this.state.url).then(mangaGet => {
      console.log("coucou", mangaGet);
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
      <div style={{ textAlign: "center" }}>
        <h1>Welcome to the Send To Reader</h1>
        <Button bsStyle="primary" onClick={this.open}>Connect</Button>
          <Modal
            onHide={this.close}
            style={modalStyle()}
            aria-labelledby="modal-label"
            show={this.state.showModal}
            renderBackdrop={this.renderBackdrop}
            >
          <div>
            <h4 id="modal-label">Text in a modal</h4>
            <p>
              Duis mollis, est non commodo luctus, nisi erat porttitor ligula.
            </p>
          </div>
        </Modal>
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

        {this.state.manga && (
          <Manga>
            <Panel>
              <Image src={this.state.manga.image} responsive />
              <div className="mangaName">{this.state.manga.name} </div>
              <span className="title">Author:</span>
              <span className="text"> {this.state.manga.author}</span>
              <ListGroup>
                {!this.state.manga.chapters
                  ? ""
                  : this.state.manga.chapters.map(url => {
                      let chapterNum = url.split("/");
                      chapterNum = chapterNum[chapterNum.length - 1];
                      return (
                        <ListGroupItem href={url} active key={chapterNum}>
                          Chapter {chapterNum}
                        </ListGroupItem>
                      );
                    })}
              </ListGroup>
            </Panel>
          </Manga>
        )}
      </div>
    );
  }
}
