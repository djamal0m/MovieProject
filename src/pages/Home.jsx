import axios from 'axios'
import React, { useEffect, useState } from 'react'
import Main from '../components/Main'
import Row from '../components/Row'
import requests from '../Requests'

const Home = () => {
  const [movies, setMovies] = useState([])

  useEffect(() => {
    const fetchData = async () => {
        const { data } = await axios.get(requests.requestPopular)
        setMovies(data)
    }
    fetchData();
},[])

  
  return ( movies.length ?
    <> 
        <Main movies={movies}/>
        <Row rowID='1' title='Up Coming' fetchURL={requests.requestUpcoming}/>
        <Row rowID='2' title='Trending' fetchURL={requests.requestTrending}/>
        <Row rowID='3' title='Action' fetchURL={requests.requestAction}/>
        <Row rowID='4' title='Popular' fetchURL={requests.requestPopular}/>
        <Row rowID='5' title='Top Rated' fetchURL={requests.requestTopRated}/>
    </>
   : null) 
}

export default Home