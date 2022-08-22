const requests = {
    requestPopular : `${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/popular`,
    requestTopRated: `${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/top-rated`,
    requestTrending: `${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/top-rated`,
    requestAction: `${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/Action`,
    requestUpcoming: `${process.env.REACT_APP_JAVA_BACKEND_URL}/netflix/movies/upcoming`,
};

export default requests