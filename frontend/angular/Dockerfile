# BUILD STAGE
FROM node:16-alpine as node

WORKDIR /usr/app

COPY package*.json ./

RUN npm install

COPY ./ ./

RUN npm run build


# DEPLOYMENT STAGE
FROM nginx:1.20-alpine

COPY ./nginx/default.conf /etc/nginx/conf.d/default.conf

COPY --from=node /usr/app/dist/build /usr/share/nginx/html

EXPOSE 80 443