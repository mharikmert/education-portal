import { Injectable } from '@angular/core';
import { HttpEvent, HttpInterceptor, HttpHandler, HttpRequest, HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable()
export class CacheInterceptor implements HttpInterceptor {
    private cache = new Map<string, any>();

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (!isCacheable(req)) { return next.handle(req); }

        const cachedResponse = this.cache.get(req.url);
        return cachedResponse ? of(cachedResponse) : sendRequest(req, next, this.cache);
    }
}

function isCacheable(req: HttpRequest<any>) {
    return req.method === 'GET';
}

function sendRequest(
    req: HttpRequest<any>,
    next: HttpHandler,
    cache: Map<string, any>): Observable<HttpEvent<any>> {

    return next.handle(req).pipe(
        tap(event => {
            if (event instanceof HttpResponse) {
                cache.set(req.url, event);
            }
        })
    );
}

