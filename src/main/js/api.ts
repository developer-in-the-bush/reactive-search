export const fetchAllDocuments = () : Promise<Response> => {
    return fetch('/documents', {method: 'get'});
};

export const createDocument = (content: string) : Promise<Response> => {
    const postifiedContent = new FormData();
    postifiedContent.append("content", content);
    return fetch('/documents', {method: 'post', body: postifiedContent});
};

export const searchDocuments = (tokens: string) : Promise<Response> => {
    return fetch('/documents/search/' + encodeURIComponent(tokens), {method: 'get'});
};
