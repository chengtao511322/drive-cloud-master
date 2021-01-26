import request from '@/utils/request'


// 分页查询banner 轮播图列表
export function listPageBanner(query) {
  return request({
    url: '/basics/banner/pageList',
    method: 'post',
    params: query
  })
}


// 分页查询banner 轮播图列表
export function listBanner(query) {
  return request({
    url: '/basics/banner/findList',
    method: 'post',
    params: query
  })
}

// 查询banner 轮播图详细
export function getBanner(id) {
  return request({
    url: '/basics/banner/' + id,
    method: 'get'
  })
}

// 新增banner 轮播图
export function addBanner(data) {
  return request({
    url: '/basics/banner',
    method: 'post',
    data: data
  })
}

// 修改banner 轮播图
export function updateBanner(data) {
  return request({
    url: '/basics/banner',
    method: 'put',
    data: data
  })
}

// 删除banner 轮播图
export function delBanner(id) {
  return request({
    url: '/basics/banner/' + id,
    method: 'delete'
  })
}


// 状态启用/停用banner 轮播图
export function changeStatus(data) {
  return request({
    url: '/basics/banner/changeStatus',
    method: 'post',
    data: data
  })
}

// 导出banner 轮播图
export function exportBanner(query) {
  return request({
    url: '/basics/banner/exportXls',
    method: 'post',
    params: query,
    responseType: 'blob'
  })
}