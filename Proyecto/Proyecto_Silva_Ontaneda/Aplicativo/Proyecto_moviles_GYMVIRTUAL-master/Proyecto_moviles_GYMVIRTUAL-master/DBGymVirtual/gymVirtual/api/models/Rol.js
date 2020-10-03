/**
 * Rol.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    id_rol:{
      type: 'number',
      autoIncrement:true,
      required: true,
      columnName: 'idRol',
    },
    nombre_rol:{
      type: 'string',
      required: true,
      columnName: 'nombreRol',
      maxLength:50
    },
    
    rolUsuarios:{
      collection: 'rolUsuario',
      via: 'rol'
    }
  },

};

