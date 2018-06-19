import * as React from "react";
import {Listing} from "./listing";
import {Button} from "@material-ui/core";
import FormInputButton from "./field-and-button";
import {createDocument, fetchAllDocuments, searchDocuments} from "../api";
import {TokensDocument} from "../model";

export interface DocumentsMap {
    [key: number]: TokensDocument;
}

interface LayoutState {
    documents: DocumentsMap;
    listingTitle: string
}

export class Layout extends React.PureComponent<{}, LayoutState> {

    constructor(props: any) {
        super(props);
        this.state = {
            documents: {},
            listingTitle: ""
        };
        this.createDocument = this.createDocument.bind(this);
        this.searchDocuments = this.searchDocuments.bind(this);
        this.updateDocuments = this.updateDocuments.bind(this);
        this.listAllDocuments = this.listAllDocuments.bind(this);
    }

    componentDidMount() {
        this.listAllDocuments();
    }

    listAllDocuments() {
        fetchAllDocuments().then(response => {
            return response.json().then(this.updateDocuments).then(() => {
                this.setState({
                    listingTitle: "All Documents"
                })
            });
        })
    }

    updateDocuments(documents: TokensDocument[]) {
        const newDocuments = documents.reduce((a, b) => {
            a[b.id] = b;
            return a;
        }, {} as DocumentsMap);
        this.setState({
            documents: newDocuments
        });
    }

    createDocument(content: string) {
        createDocument(content).then(response => {
            return response.json().then((document: TokensDocument) => {
                this.setState({
                    documents: {
                        ...this.state.documents,
                        [document.id]: document
                    },
                    listingTitle: "Documents"
                })
            });
        })
    }

    searchDocuments(tokens: string) {
        searchDocuments(tokens).then(response => {
            return response.json().then(this.updateDocuments).then(() => {
                this.setState({
                    listingTitle: "Search results"
                })
            })
        })
    }

    render() {
        return <div id="layout-container">
            <FormInputButton buttonName={"Create"} label={"Tokens to store"} buttonClickHandler={this.createDocument}/>
            <FormInputButton buttonName={"Search"} label={"Tokens to search"} buttonClickHandler={this.searchDocuments}/>
            <br/>
            <Button onClick={this.listAllDocuments}>List all documents</Button>
            {this.state.documents &&
            <Listing elements={Object.keys(this.state.documents).map(key => this.state.documents[+key])}
                     title={this.state.listingTitle}/>}
        </div>;
    }
}